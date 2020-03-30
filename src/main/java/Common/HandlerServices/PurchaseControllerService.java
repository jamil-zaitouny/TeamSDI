package Common.HandlerServices;

import Model.Purchase;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

public interface PurchaseControllerService {
    String PRINT_PURCHASES = "printPurchases";
    String ADD_PURCHASE = "addPurchase";
    String DELETE_PURCHASE = "removePurchase";
    String UPDATE_PURCHASE = "updatePurchase";
    String CLIENTS_WITH_MOST_PURCHASES = "clientsWithMostPurchases";
    String BOOKS_WITH_HIGHEST_PURCHASE_COUNT = "booksWithHighestPurchaseCount";
    String BOOKS_WITH_HIGHEST_PURCHASE_COUNT_PER_GENRE = "booksWithHighestPurchaseCountPerGenre";

    Future<Set<Purchase>> printPurchases();
    Future<Void> addPurchase(Purchase purchase);
    Future<Void> deletePurchase(int ID);
    Future<Void> updatePurchase(int ID, String purchaseDetails);
    Future<List<String>> clientsWithMostPurchases();
    Future<List<String>> booksWithHighestPurchaseCount();
    Future<List<String>> booksWithHighestPurchaseCountPerGenre();
    Future<Void>deleteAllPurchasesForBook(String ibsn);
    Future<Void> deleteAllPurchasesForClient(int id);
    Future<Iterable<Purchase>> sortPurchasesByDescription();
}
