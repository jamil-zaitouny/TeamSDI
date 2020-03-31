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
import java.util.concurrent.Future;

public class PurchaseControllerHandler implements PurchaseControllerService {
    private ExecutorService executorService;
    private TCPClient tcpClient;

    public PurchaseControllerHandler(ExecutorService executorService, TCPClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public Future<Set<Purchase>> printPurchases() {
        return executorService.submit(() -> {
            Message request = new Message(PurchaseControllerService.PRINT_PURCHASES, "");
            Message response = tcpClient.sendAndReceive(request);

            Set<Purchase> purchases ;
            purchases = (Set<Purchase>)response.getBody();
            return purchases;
        });

    }

    @Override
    public Future<Void> addPurchase(Purchase purchase) {
        int ID=purchase.getId();
        String ISBN=purchase.getBookId();
        int clientID=purchase.getClientId();
        String purchaseDetails=purchase.getPurcahseDetails();
        return executorService.submit(() -> {
            Message request = new Message(PurchaseControllerService.ADD_PURCHASE, ID + " " + ISBN + " " + clientID + " " + purchaseDetails);
            tcpClient.sendAndReceive(request);
            return null;
        });
    }

    @Override
    public Future<Void> deletePurchase(int ID) {
        return executorService.submit(() -> {
            Message request = new Message(PurchaseControllerService.DELETE_PURCHASE, String.valueOf(ID));
            tcpClient.sendAndReceive(request);
            return null;
        });
    }

    @Override
    public Future<Void> updatePurchase(int ID, String purchaseDetails) {
        return executorService.submit(() -> {
            Message request = new Message(PurchaseControllerService.ADD_PURCHASE, ID + " " + purchaseDetails);
            tcpClient.sendAndReceive(request);
            return null;
        });
    }

    @Override
    public Future<List<String>> clientsWithMostPurchases() {
        return null;
    }

    @Override
    public Future<List<String>> booksWithHighestPurchaseCount() {
        return null;
    }

    @Override
    public Future<List<String>> booksWithHighestPurchaseCountPerGenre() {
        return null;
    }

    @Override
    public Future<Void> deleteAllPurchasesForBook(String ibsn) {
        return null;
    }

    @Override
    public Future<Void> deleteAllPurchasesForClient(int id) {
        return null;
    }

    @Override
    public Future<Iterable<Purchase>> sortPurchasesByDescription() {
        return null;
    }
}
