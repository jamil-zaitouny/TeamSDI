package Controller;

import Model.Book;
import Model.Exceptions.ValidatorException;
import Model.Purchase;
import Model.Validators.PurchaseValidator;
import Repository.RepositoryInterface;
import Repository.SortRepository.Sort;
import Repository.SortRepository.SortingRepository;

import javax.print.DocFlavor;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PurchaseController
{
    private SortingRepository<Integer, Purchase> repository;
    private ClientController clients;
    private BookController books;
    private PurchaseValidator validator;

    public PurchaseController(SortingRepository<Integer, Purchase> repository,ClientController clients,  BookController books) {
        this.repository = repository;
        this.clients = clients;
        this.books = books;
        validator = new PurchaseValidator();
    }

    public void addPurchase(Purchase purchase) throws Throwable {
        clients.findOne(purchase.getClientId()).orElseThrow(()->new ValidatorException("Client does not exist!"));
        books.findOne(purchase.getBookId()).orElseThrow(()->new ValidatorException("Book does not exist!"));
        validator.validate(purchase);
        this.repository.add(purchase);
    }

    public Set<Purchase> getAllPurchases()
    {
        return StreamSupport.stream(sortPurchasesByDescription().spliterator(), false).collect(Collectors.toSet());
    }

    public void deletePurchase(Integer id)
    {
        this.repository.delete(id);
    }

    public void deleteAllPurchasesForClient(Integer id)
    {
        Iterable<Purchase> purchases=repository.findAll();
        StreamSupport.stream(purchases.spliterator(),false)
                .filter(purchase-> purchase.getClientId()==id)
                .forEach(purchase-> deletePurchase(purchase.getId()));
    }

    public void deleteAllPurchasesForBook(String id)
    {
        Iterable<Purchase> purchases=repository.findAll();
        StreamSupport.stream(purchases.spliterator(),false)
                .filter(purchase-> purchase.getBookId().equals(id))
                .forEach(purchase-> deletePurchase(purchase.getId()));
    }

    public void updatePurchase(Integer id, String newDetails)
    {
        Purchase newPurchase = new Purchase(id,this.repository.findOne(id).get().getBookId(),this.repository.findOne(id).get().getClientId(),newDetails);
        this.validator.validate(newPurchase);
        this.repository.update(newPurchase);
    }

    public Optional findOne(int purchaseID){
        return repository.findOne(purchaseID);
    }


    public Set<String> getTopThreeBooksBought()
    {
        List<String> topThree;
        SortedSet<Map.Entry<String, Integer>> report = new TreeSet<>(((o1, o2) -> {
            if(o1.getValue().compareTo(o2.getValue())==0)
            {
                return o1.getKey().compareTo(o2.getKey());
            }
            return o2.getValue().compareTo(o1.getValue());
        }));
        Map<String, Integer> bookSold = getBooksBought();
        report.addAll(bookSold.entrySet());
        topThree = report.stream().map(entry -> this.books.findOne(entry.getKey()).get() + "Sold: " + entry.getValue())
                .collect(Collectors.toList());
        return topThree.subList(0,3).stream().collect(Collectors.toSet());
    }


    public Set<String> getTopThreeClientsMostBooks()
    {
        List<String> topThree;
        SortedSet<Map.Entry<Integer, Integer>> report = new TreeSet<>(((o1, o2) -> {
            if(o1.getValue().compareTo(o2.getValue())==0)
            {
                return o1.getKey().compareTo(o2.getKey());
            }
            return o2.getValue().compareTo(o1.getValue());
        }));
        Map<Integer, Integer> clientsWithNumberBooks = getClientsWithNumberOfBoughtBooks();
        report.addAll(clientsWithNumberBooks.entrySet());
        topThree = report.stream().map(entry -> this.clients.searchById(entry.getKey()).getName() + ": " + entry.getValue())
                                  .collect(Collectors.toList());
        return topThree.subList(0,3).stream().collect(Collectors.toSet());
    }

    public String getClientMostBooksGenre(String genre)
    {
        List<String> topThree;
        SortedSet<Map.Entry<Integer, Integer>> report = new TreeSet<>(((o1, o2) -> {
            if(o1.getValue().compareTo(o2.getValue())==0)
            {
                return o1.getKey().compareTo(o2.getKey());
            }
            return o2.getValue().compareTo(o1.getValue());
        }));
        Map<Integer, Integer> clientsWithNumberBooks = getClientsWithNumberOfBoughtBooksWithGenre(genre);
        report.addAll(clientsWithNumberBooks.entrySet());
        return report.stream().map(entry -> this.clients.searchById(entry.getKey()).getName() + ": " + entry.getValue())
                .collect(Collectors.toList()).get(0);
    }

    private Map<String, Integer> getBooksBought()
    {
        Set<Purchase> purchases = getAllPurchases();
        Map<String, Integer> report = new TreeMap<>();
        purchases.forEach((purchase)->{
            String key = purchase.getBookId();
            report.putIfAbsent(key, 0);
            report.replace(key, report.get(key)+1);
        });
        return report;
    }
    public List<String> getBooksBoughtPerGenre()
    {
        Set<Purchase> purchases = getAllPurchases();
        Map<String, Integer> genreNoBooks = new TreeMap<>();
        purchases.forEach((purchase)->{
            String id = purchase.getBookId();
            Book book = this.books.searchByIbsn(id);
            String key = book.getGenre();
            genreNoBooks.putIfAbsent(key, 0);
            genreNoBooks.replace(key, genreNoBooks.get(key)+1);
        });
        return genreNoBooks.entrySet().stream().map(entry->entry.getKey()+": "+ entry.getValue()).collect(Collectors.toList());
    }

    private Map<Integer, Integer> getClientsWithNumberOfBoughtBooks()
    {
        Set<Purchase> purchases = getAllPurchases();
        Map<Integer, Integer> report = new TreeMap<>();
        purchases.forEach((purchase)->{
            int key = purchase.getClientId();
            report.putIfAbsent(key, 0);
            report.replace(key, report.get(key)+1);
        });
        return report;
    }

    private Map<Integer, Integer> getClientsWithNumberOfBoughtBooksWithGenre(String genre)
    {
        Set<Purchase> purchases = getAllPurchases();
        Map<Integer, Integer> report = new TreeMap<>();
        purchases.stream().filter(v->{
            String bookid = v.getBookId();
            String bookGenre = ((Book)this.books.findOne(bookid).get()).getGenre();
            return genre.equals(bookGenre);
        }).forEach((purchase)->{
            int key = purchase.getClientId();

            report.putIfAbsent(key, 0);
            report.replace(key, report.get(key)+1);
        });
        return report;
    }
    public Iterable<Purchase> sortPurchasesByDescription() {
        Sort sort=new Sort(Sort.Direction.ASC,"purcahseDetails");
        return repository.findAll(sort);
    }
}
