package Client.service;

import Client.TCP.TCPClient;
import Common.Communication.Message;
import Common.HandlerServices.ClientControllerService;
import Common.HandlerServices.PurchaseControllerService;
import Model.Book;
import Model.Purchase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.CompletableFuture;

public class PurchaseControllerHandler implements PurchaseControllerService {
    private ExecutorService executorService;
    private TCPClient tcpClient;

    public PurchaseControllerHandler(ExecutorService executorService, TCPClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public CompletableFuture<Set<Purchase>> printPurchases() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(PurchaseControllerService.PRINT_PURCHASES, "");
            Message response = tcpClient.sendAndReceive(request);

            Set<Purchase> purchases;
            purchases = (Set<Purchase>) response.getBody();
            return purchases;
        });

    }

    @Override
    public CompletableFuture<Void> addPurchase(Purchase purchase) {
        int ID = purchase.getId();
        String ISBN = purchase.getBookId();
        int clientID = purchase.getClientId();
        String purchaseDetails = purchase.getPurcahseDetails();
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(PurchaseControllerService.ADD_PURCHASE, ID + " " + ISBN + " " + clientID + " " + purchaseDetails);
            tcpClient.sendAndReceive(request);
            return null;
        });
    }

    @Override
    public CompletableFuture<Void> deletePurchase(int ID) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(PurchaseControllerService.DELETE_PURCHASE, String.valueOf(ID));
            tcpClient.sendAndReceive(request);
            return null;
        });
    }

    @Override
    public CompletableFuture<Void> updatePurchase(int ID, String purchaseDetails) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(PurchaseControllerService.ADD_PURCHASE, ID + " " + purchaseDetails);
            tcpClient.sendAndReceive(request);
            return null;
        });
    }

    @Override
    public CompletableFuture<Set<String>> clientsWithMostPurchases() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(PurchaseControllerService.CLIENTS_WITH_MOST_PURCHASES, "");
            Message response = tcpClient.sendAndReceive(request);

            Set<String> purchases;
            purchases = (Set<String>) response.getBody();
            System.out.println(purchases);
            return purchases;
        });
    }

    @Override
    public CompletableFuture<Set<String>> booksWithHighestPurchaseCount() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(PurchaseControllerService.BOOKS_WITH_HIGHEST_PURCHASE_COUNT, "");
            Message response = tcpClient.sendAndReceive(request);

            Set<String> purchases;
            purchases = (Set<String>) response.getBody();
            return purchases;
        });
    }

    @Override
    public CompletableFuture<List<String>> booksWithHighestPurchaseCountPerGenre() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(PurchaseControllerService.BOOKS_WITH_HIGHEST_PURCHASE_COUNT_PER_GENRE, "");
            Message response = tcpClient.sendAndReceive(request);

            List<String> purchases;
            purchases = (List<String>) response.getBody();
            return purchases;
        });
    }

    @Override
    public CompletableFuture<Void> deleteAllPurchasesForBook(String ibsn) {
        return null;
    }

    @Override
    public CompletableFuture<Void> deleteAllPurchasesForClient(int id) {
        return null;
    }

    @Override
    public CompletableFuture<Iterable<Purchase>> sortPurchasesByDescription() {
        return null;
    }
}
