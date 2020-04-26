package springjpa.Repository.DBRepository;

import springjpa.Model.Exceptions.FileException;
import springjpa.Model.Purchase;
import springjpa.Repository.SortRepository.Sort;
import springjpa.Repository.SortRepository.SortingRepository;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class PurchaseDBRepository implements SortingRepository<Integer, Purchase>
{
    HashMap<Integer,Purchase> purchases;
    Connection connection;


    @Override
    public Optional<Purchase> findOne(Integer id) {
        return Optional.ofNullable(purchases.get(id));
    }

    private void loadPurchases() throws FileException {
        try {
            String selectPurchases="select * from purchases";
            PreparedStatement selectPurchasesStatement=connection.prepareStatement(selectPurchases);
            ResultSet purchasesSet=selectPurchasesStatement.executeQuery();
            System.out.println(purchasesSet);
            while(purchasesSet.next()) {
                int id=purchasesSet.getInt("purchaseid");
                String bookid=purchasesSet.getString("bookid");
                int clientid=purchasesSet.getInt("clientid");
                String purchasedetails=purchasesSet.getString("purchasedetails");
                purchases.put(id,new Purchase(id,bookid,clientid,purchasedetails));
            }
        } catch (SQLException e) {
            throw new FileException("There was some problem with the database!");
        }
    }

    public PurchaseDBRepository() throws FileException {
        try {
            purchases =new HashMap<>();
            //Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost/bookshop", "postgres", "admin");
            //connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bookshop?user=postgres&password=admin");
        } catch (SQLException e) {
            throw new FileException("Could not connect to the database!");
        }
        try {
            loadPurchases();
        } catch (FileException e) {
            throw e;
        }
    }

    @Override
    public Iterable<Purchase> findAll(Sort sort) {
        return sort.sort(purchases.values().stream()
                .map(purchase -> (Object) purchase)
                .collect(Collectors.toList()))
                .stream().map(purchase -> (Purchase) purchase)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Purchase> findAll() {
        Set<Purchase> allEntities = new HashSet<>();

        try {
            String selectPurchases="select * from purchases";
            PreparedStatement selectPurchasesStatement=connection.prepareStatement(selectPurchases);
            ResultSet purchasesSet=selectPurchasesStatement.executeQuery();
            System.out.println(purchasesSet);
            while(purchasesSet.next()) {
                int id=purchasesSet.getInt("purchaseid");
                String bookid=purchasesSet.getString("bookid");
                int clientid=purchasesSet.getInt("clientid");
                String purchasedetails=purchasesSet.getString("purchasedetails");
                allEntities.add(new Purchase(id,bookid,clientid,purchasedetails));
            }
        } catch (SQLException e) {
            throw new FileException("There was some problem with the database!");
        }
        return allEntities;
    }


    @Override
    public Optional<Purchase> add(Purchase entity) throws FileException {
        System.out.println(entity);
        Optional<Purchase> previous=Optional.ofNullable(purchases.putIfAbsent(entity.getId(), entity));
        previous.ifPresentOrElse(purchase -> {}, () -> {
            String addPurchases = "insert into purchases (purchaseid, bookid,clientid,purchasedetails) values(?,?,?,?)";
            try {
                PreparedStatement addPurchasesStatement = connection.prepareStatement(addPurchases);
                addPurchasesStatement.setInt(1, entity.getId());
                addPurchasesStatement.setString(2, entity.getBookId());
                addPurchasesStatement.setInt(3, entity.getClientId());
                addPurchasesStatement.setString(4, entity.getPurcahseDetails());
                addPurchasesStatement.executeUpdate();
            } catch (SQLException e) {
                throw new FileException("There was some problem with the database!");
            }
        });
        return previous;
    }

    @Override
    public Optional<Purchase> delete(Integer id) {
        Optional<Purchase> previous=Optional.ofNullable(purchases.remove(id));
        previous.ifPresent(purchase -> {
            try {
                PreparedStatement deletePurchaseStatement=connection.prepareStatement("delete from purchases where purchaseid=?");
                deletePurchaseStatement.setInt(1,id);
                deletePurchaseStatement.executeUpdate();
            } catch (SQLException e) {
                throw new FileException("There was some problem with the database!");
            }
        });
        return previous;
    }

    @Override
    public Optional<Purchase> update(Purchase entity) throws FileException {
        Optional<Purchase> savedValue=Optional.ofNullable(purchases.computeIfPresent(entity.getId(), (k, v) -> entity));
        savedValue.ifPresent( purchase -> {
            try {
                PreparedStatement purchaseUpdateStatement=connection.prepareStatement("update purchases set purchasedetails=? where purchaseid=?");
                purchaseUpdateStatement.setString(1,entity.getPurcahseDetails());
                purchaseUpdateStatement.setInt(2,entity.getId());
                purchaseUpdateStatement.executeUpdate();
            } catch (SQLException e) {
                throw new FileException("There was some problem with the database!");
            }
        });
        return savedValue;
    }
}
