package springjpa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springjpa.Model.Book;
import springjpa.Model.Exceptions.ValidatorException;
import springjpa.Model.Purchase;
import springjpa.Model.Validators.PurchaseValidator;
import springjpa.Repository.DBRepository.PurchaseDBRepository;
import springjpa.Repository.SortRepository.Sort;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class PurchaseController
{
    @Autowired
    private PurchaseDBRepository repository;
    @Autowired
    private ClientController clients;
    @Autowired
    private BookController books;
    @Autowired
    private PurchaseValidator validator;

    /*public PurchaseController(SortingRepository<Integer, Purchase> repository,ClientController clients,  BookController books) {
        this.repository = repository;
        this.clients = clients;
        this.books = books;
        validator = new PurchaseValidator();
    }*/

    public void addPurchase(Purchase purchase) throws Throwable {
        clients.findOne(purchase.getClientId()).orElseThrow(()->new ValidatorException("Client does not exist!"));
        books.findOne(purchase.getBookId()).orElseThrow(()->new ValidatorException("Book does not exist!"));
        validator.validate(purchase);
        Optional<Purchase> previous=repository.findById(purchase.getId());
        previous.ifPresent(s -> {
            throw new ValidatorException("ID already exists.");
        });
        repository.save(purchase);
    }

    public List<Purchase> getAllPurchases()
    {
        return repository.findAll();
    }

    public void deletePurchase(Integer id)
    {
        Optional<Purchase> previous=repository.findById(id);
        previous.orElseThrow(() -> {
            throw new ValidatorException("Could not find purchase based on ID.");
        });
        repository.deleteById(id);
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

    @Transactional
    public void updatePurchase(Integer id, String newDetails)
    {
        Purchase newPurchase = new Purchase(id,this.repository.findById(id).get().getBookId(),this.repository.findById(id).get().getClientId(),newDetails);
        repository.findById(newPurchase.getId())
                .ifPresentOrElse(s -> {
                    s.setPurcahseDetails(newPurchase.getPurcahseDetails());
                }, () -> {
                    throw new ValidatorException("Could not find purchase based on ID.");
                });
    }

    public Optional findOne(int purchaseID){
        return repository.findById(purchaseID);
    }


    public List<String> getTopThreeBooksBought()
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
        return topThree.subList(0,3);
    }


    public List<String> getTopThreeClientsMostBooks()
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
        return topThree.subList(0,3);
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
        List<Purchase> purchases = getAllPurchases();
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
        List<Purchase> purchases = getAllPurchases();
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
        List<Purchase> purchases = getAllPurchases();
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
        List<Purchase> purchases = getAllPurchases();
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
        Sort sort=new Sort("purcahseDetails");
        return sort.sort(repository.findAll().stream()
                .map(s -> (Object)s)
                .collect(Collectors.toList()))
                .stream().map(s->(Purchase)s)
                .collect(Collectors.toList());
    }
}
