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
    public Future<Set<Purchase>> printPurchases() {
        return executorService.submit(()->purchaseController.getAllPurchases());
    }

    @Override
    public Future<Void> addPurchase(int ID, String ISBN, int clientID, String purchaseDetails) {
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
    public Future<Void> deletePurchase(int ID) {
        return executorService.submit(()->{
            purchaseController.deletePurchase(ID);
            return null;
        });
    }

    @Override
    public Future<Void> updatePurchase(int ID, String purchaseDetails) {
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
    public Future<List<String>> clientsWithMostPurchases() {
        return executorService.submit(()->purchaseController.getTopThreeClientsMostBooks());
    }

    @Override
    public Future<List<String>> booksWithHighestPurchaseCount() {
        return executorService.submit(()->purchaseController.getTopThreeBooksBought());
    }

    @Override
    public Future<List<String>> booksWithHighestPurchaseCountPerGenre() {
        return executorService.submit(()->purchaseController.getBooksBoughtPerGenre());
    }
}
