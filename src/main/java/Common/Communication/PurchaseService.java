package Common.Communication;

import Model.Purchase;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface PurchaseService {
    Set<Purchase> printPurchases();
    void addPurchase(Purchase purchase);
    void deletePurchase(int ID);
    void updatePurchase(int ID, String purchaseDetails);
    Set<String> clientsWithMostPurchases();
    Set<String> booksWithHighestPurchaseCount();
    List<String> booksWithHighestPurchaseCountPerGenre();
    void deleteAllPurchasesForBook(String ibsn);
    void deleteAllPurchasesForClient(int id);
    Iterable<Purchase> sortPurchasesByDescription();
    Optional findOne(int purchaseID);
    Set<Purchase> getAllClients();
}
