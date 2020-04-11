package Common.Communication;

import Model.Purchase;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface PurchaseService {
    CompletableFuture<Set<Purchase>> printPurchases();
    CompletableFuture<Void> addPurchase(Purchase purchase);
    CompletableFuture<Void> deletePurchase(int ID);
    CompletableFuture<Void> updatePurchase(int ID, String purchaseDetails);
    CompletableFuture<Set<String>> clientsWithMostPurchases();
    CompletableFuture<Set<String>> booksWithHighestPurchaseCount();
    CompletableFuture<List<String>> booksWithHighestPurchaseCountPerGenre();
    CompletableFuture<Void>deleteAllPurchasesForBook(String ibsn);
    CompletableFuture<Void> deleteAllPurchasesForClient(int id);
    CompletableFuture<Iterable<Purchase>> sortPurchasesByDescription();
}
