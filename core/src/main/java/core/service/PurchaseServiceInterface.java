package core.service;

import core.model.Purchase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PurchaseServiceInterface {

    public void addPurchase(Purchase purchase) throws Throwable;
    public List<Purchase> getAllPurchases();
    public void deletePurchase(Integer id);
    public void updatePurchase(Purchase purchase) throws Throwable;
    public Optional findOne(int purchaseID);
    public List<String> getTopThreeBooksBought();
    public List<String> getTopThreeClientsMostBooks();
    public Map<Integer, Integer> getClientsWithNumberOfBoughtBooksWithGenre(String genre);
}
