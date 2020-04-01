package Common.HandlerServices;

import Model.Purchase;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletableFuture;

public interface PurchaseControllerService {
    String PRINT_PURCHASES = "printPurchases";
    String ADD_PURCHASE = "addPurchase";
    String DELETE_PURCHASE = "removePurchase";
    String UPDATE_PURCHASE = "updatePurchase";
    String CLIENTS_WITH_MOST_PURCHASES = "clientsWithMostPurchases";
    String BOOKS_WITH_HIGHEST_PURCHASE_COUNT = "booksWithHighestPurchaseCount";
    String BOOKS_WITH_HIGHEST_PURCHASE_COUNT_PER_GENRE = "booksWithHighestPurchaseCountPerGenre";

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
