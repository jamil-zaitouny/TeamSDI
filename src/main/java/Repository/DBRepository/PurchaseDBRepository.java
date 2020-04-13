package Repository.DBRepository;

import Model.Book;
import Model.Exceptions.FileException;
import Model.Purchase;
import Repository.SortRepository.Sort;
import Repository.SortRepository.SortingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class PurchaseDBRepository implements SortingRepository<Integer, Purchase> {
    HashMap<Integer, Purchase> purchases;


    @Autowired
    JdbcOperations jdbcOperations;

    @Override
    public Optional<Purchase> findOne(Integer id) {
        return Optional.ofNullable(purchases.get(id));
    }

    private void loadPurchases() throws FileException {
        String selectPurchases = "select * from purchases";
        jdbcOperations.query(selectPurchases, (rs, rowNum) -> {
            int id = rs.getInt("purchaseid");
            String bookid = rs.getString("bookid");
            int clientid = rs.getInt("clientid");
            String purchasedetails = rs.getString("purchasedetails");
            Purchase purchase = new Purchase(id, bookid, clientid, purchasedetails);
            purchases.put(id, purchase);
            return purchase;
        });
    }

    public PurchaseDBRepository() throws FileException {
        //loadPurchases();
    }

    @Override
    public Iterable<Purchase> findAll(Sort sort) {
        /*return sort.sort(purchases.values().stream()
                .map(purchase -> (Object) purchase)
                .collect(Collectors.toList()))
                .stream().map(purchase -> (Purchase) purchase)
                .collect(Collectors.toList());
    */
        return sort.sort(StreamSupport.stream(findAll().spliterator(), false).collect(Collectors.toList()))
                .stream().map(purchase-> (Purchase) purchase)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Purchase> findAll() {
        String selectPurchases = "select * from purchases";
        return new HashSet<>(jdbcOperations.query(selectPurchases, (rs, rowNum) -> {
            int id = rs.getInt("purchaseid");
            String bookid = rs.getString("bookid");
            int clientid = rs.getInt("clientid");
            String purchasedetails = rs.getString("purchasedetails");
            return new Purchase(id, bookid, clientid, purchasedetails);
        }));
    }


    @Override
    public Optional<Purchase> add(Purchase entity) throws FileException {
        System.out.println(entity);
        Optional<Purchase> previous = Optional.ofNullable(purchases.putIfAbsent(entity.getId(), entity));
        previous.ifPresentOrElse(purchase -> {
        }, () -> {
            String addPurchases = "insert into purchases (purchaseid, bookid,clientid,purchasedetails) values(?,?,?,?)";
            jdbcOperations.update(addPurchases, entity.getId(), entity.getBookId(), entity.getClientId(), entity.getPurcahseDetails());
        });
        return previous;
    }

    @Override
    public Optional<Purchase> delete(Integer id) {
        Optional<Purchase> previous = Optional.ofNullable(purchases.remove(id));
        previous.ifPresent(purchase -> {
            String sql = "delete from purchases where purchaseid=?";
            jdbcOperations.update(sql, id);
        });
        return previous;
    }

    @Override
    public Optional<Purchase> update(Purchase entity) throws FileException {
        Optional<Purchase> savedValue = Optional.ofNullable(purchases.computeIfPresent(entity.getId(), (k, v) -> entity));
        savedValue.ifPresent(purchase -> {
            String sql = "update purchases set purchasedetails=? where purchaseid=?";
            jdbcOperations.update(sql, entity.getPurcahseDetails(), entity.getId());
        });
        return savedValue;
    }
}
