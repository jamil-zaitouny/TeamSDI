package Client.service;
import Common.Communication.PurchaseService;
import Model.Purchase;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class PurchaseServiceClient {
    PurchaseService purchaseService;

    public PurchaseServiceClient(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    public CompletableFuture<Set<Purchase>> printPurchases() {
        return CompletableFuture.supplyAsync(()->purchaseService.printPurchases());
    }

    public CompletableFuture<Void> addPurchase(Purchase purchase) {
        return CompletableFuture.runAsync(()->purchaseService.addPurchase(purchase));
    }

    public CompletableFuture<Void> deletePurchase(int ID) {
        return CompletableFuture.runAsync(()->purchaseService.deletePurchase(ID));
    }

    public CompletableFuture<Void> updatePurchase(int ID, String purchaseDetails) {
        return CompletableFuture.runAsync(()->purchaseService.updatePurchase(ID,purchaseDetails));
    }

    public CompletableFuture<Set<String>> clientsWithMostPurchases() {
        return CompletableFuture.supplyAsync(()->purchaseService.clientsWithMostPurchases());
    }

    public CompletableFuture<Set<String>> booksWithHighestPurchaseCount() {
        return CompletableFuture.supplyAsync(()->purchaseService.booksWithHighestPurchaseCount());
    }

    public CompletableFuture<List<String>> booksWithHighestPurchaseCountPerGenre() {
        return CompletableFuture.supplyAsync(()->purchaseService.booksWithHighestPurchaseCountPerGenre());
    }

    public CompletableFuture<Void> deleteAllPurchasesForBook(String ibsn) {
        return CompletableFuture.runAsync(()->purchaseService.deleteAllPurchasesForBook(ibsn));
    }

    public CompletableFuture<Void> deleteAllPurchasesForClient(int id) {
        return CompletableFuture.runAsync(()->purchaseService.deleteAllPurchasesForClient(id));
    }

    public CompletableFuture<Iterable<Purchase>> sortPurchasesByDescription() {
        return CompletableFuture.supplyAsync(()->purchaseService.sortPurchasesByDescription());
    }

    public CompletableFuture<Optional> findOne(int purchaseID) {
        return CompletableFuture.supplyAsync(()->purchaseService.findOne(purchaseID));
    }

    public CompletableFuture<Set<Purchase>> getAllClients() {
        return CompletableFuture.supplyAsync(()->purchaseService.getAllPurchases());
    }
}
