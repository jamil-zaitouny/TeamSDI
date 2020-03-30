package Common.HandlerServices;

import Model.Book;
import Model.Purchase;

import java.util.List;
import java.util.Map;
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

    Future<Set<Purchase>> print_purchases();
    Future<Void> add_purchase(int ID, String ISBN, int clientID, String purchaseDetails);
    Future<Void> delete_purchase(int ID);
    Future<Void> update_purchase(int ID, String purchaseDetails);
    Future<List<String>> clients_with_most_purchases();
    Future<List<String>> books_with_highest_purchase_count();
    Future<List<String>> books_with_highest_purchase_count_per_genre();
}
