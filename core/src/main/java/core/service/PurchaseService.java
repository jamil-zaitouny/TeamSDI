package core.service;

import core.model.Book;
import core.model.Purchase;
import core.repository.PurchaseDBRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import core.model.Exceptions.ValidatorException;
import core.model.Validators.PurchaseValidator;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class PurchaseService implements PurchaseServiceInterface
{
    @Autowired
    private PurchaseDBRepository repository;
    @Autowired
    private ClientService clients;
    @Autowired
    private BookService books;
    @Autowired
    private PurchaseValidator validator;

    final Logger logger = LoggerFactory.getLogger(PurchaseService.class);

    public void addPurchase(Purchase purchase) throws Throwable {
        clients.findOne(purchase.getClientId()).orElseThrow(()->{
            logger.info("ERROR: Try to add a purchase for a client that doesn't exist");
            return new ValidatorException("Client does not exist!");
        });
        books.findOne(purchase.getBookId()).orElseThrow(()->{
            logger.info("ERROR: Try to add a purchase with a book that doesn't exist");
            return new ValidatorException("Book does not exist!");
        });
        validator.validate(purchase);
        Optional<Purchase> previous=repository.findById(purchase.getId());
        previous.ifPresent(s -> {
            logger.info("ERROR: Try to add a purchase with an existent id: " + purchase.getId());
            throw new ValidatorException("ID already exists.");
        });
        logger.info("Adding a new purchase");
        repository.save(purchase);
    }

    public List<Purchase> getAllPurchases()
    {
        logger.info("Retrieve all purchases");
        return repository.findAll();
    }

    public void deletePurchase(Integer id)
    {
        Optional<Purchase> previous=repository.findById(id);
        previous.orElseThrow(() -> {
            logger.info("ERROR: Try to delete a purchase with an id that doesn't exist");
            throw new ValidatorException("Could not find purchase based on ID.");
        });
        logger.info("Deleting a purchase");
        repository.deleteById(id);
    }

    //@Transactional
    public void updatePurchase(Purchase newPurchase) throws Throwable {
        //Purchase newPurchase = new Purchase(id,this.repository.findById(id).get().getBookId(),this.repository.findById(id).get().getClientId(),newDetails);
        /*repository.findById(newPurchase.getId())
                .ifPresentOrElse(s -> {
                    s.setPurcahseDetails(newPurchase.getPurcahseDetails());
                }, () -> {
                    logger.info("Trying to update purchase with non existent: " );
                    throw new ValidatorException("Could not find purchase based on ID.");
                });
        logger.info("Updated a purchases");*/
        this.deletePurchase(newPurchase.getId());
        this.addPurchase(newPurchase);
        logger.info("Updating an existent book: ");
    }

    public Optional findOne(int purchaseID){
        logger.info("Searching for purchase with id: " + purchaseID);
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

    public Map<String, Integer> getBooksBought() {
        List<Purchase> purchases = getAllPurchases();
        Map<String, Integer> report = new TreeMap<>();
        purchases.forEach((purchase) -> {
            String key = purchase.getBookId();
            report.putIfAbsent(key, 0);
            report.replace(key, report.get(key) + 1);
        });
        return report;
    }

    public Map<Integer, Integer> getClientsWithNumberOfBoughtBooks()
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

    public Map<Integer, Integer> getClientsWithNumberOfBoughtBooksWithGenre(String genre)
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
    /*public Iterable<Purchase> sortPurchasesByDescription() {
        logger.info("Sorting purchases by description");
        Sort sort=new Sort("purcahseDetails");
        return sort.sort(repository.findAll().stream()
                .map(s -> (Object)s)
                .collect(Collectors.toList()))
                .stream().map(s->(Purchase)s)
                .collect(Collectors.toList());
      }*/

    /*public void deleteAllPurchasesForClient(Integer id)
    {
        logger.info("Deleting all purchases for client with id: " + id);
        Iterable<Purchase> purchases=repository.findAll();
        StreamSupport.stream(purchases.spliterator(),false)
                .filter(purchase-> purchase.getClientId()==id)
                .forEach(purchase-> deletePurchase(purchase.getId()));
    }

    public void deleteAllPurchasesForBook(String id)
    {
        logger.info("Deleting all purchases for book with id: " + id);
        Iterable<Purchase> purchases=repository.findAll();
        StreamSupport.stream(purchases.spliterator(),false)
                .filter(purchase-> purchase.getBookId().equals(id))
                .forEach(purchase-> deletePurchase(purchase.getId()));
    }*/
}

