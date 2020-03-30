package Server.service;

import Controller.PurchaseController;
import Common.HandlerServices.PurchaseControllerService;
import Model.Purchase;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class PurchaseControllerHandler implements PurchaseControllerService {
    private ExecutorService executorService;
    private PurchaseController purchaseController;

    public PurchaseControllerHandler(ExecutorService executorService,PurchaseController purchaseController){
        this.purchaseController = purchaseController;
        this.executorService = executorService;
    }
    @Override
    public Future<Set<Purchase>> print_purchases() {
        return executorService.submit(()->purchaseController.getAllPurchases());
    }

    @Override
    public Future<Void> add_purchase(int ID, String ISBN, int clientID, String purchaseDetails) {
        return executorService.submit(()->{
            try {
                purchaseController.addPurchase(new Purchase(ID, ISBN, clientID, purchaseDetails));
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public Future<Void> delete_purchase(int ID) {
        return executorService.submit(()->{
            purchaseController.deletePurchase(ID);
            return null;
        });
    }

    @Override
    public Future<Void> update_purchase(int ID, String purchaseDetails) {
        return executorService.submit(()->{
            try {
                purchaseController.updatePurchase(ID,  purchaseDetails);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public Future<List<String>> clients_with_most_purchases() {
        return executorService.submit(()->purchaseController.getTopThreeClientsMostBooks());
    }

    @Override
    public Future<List<String>> books_with_highest_purchase_count() {
        return executorService.submit(()->purchaseController.getTopThreeBooksBought());
    }

    @Override
    public Future<List<String>> books_with_highest_purchase_count_per_genre() {
        return executorService.submit(()->purchaseController.getBooksBoughtPerGenre());
    }
}
