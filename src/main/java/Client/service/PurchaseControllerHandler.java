package Client.service;

import Client.TCP.TCPClient;
import Common.Communication.Message;
import Common.HandlerServices.ClientControllerService;
import Common.HandlerServices.PurchaseControllerService;
import Model.Purchase;

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
        return null;
    }

    @Override
    public Future<Void> addPurchase(int ID, String ISBN, int clientID, String purchaseDetails) {
        return executorService.submit(() -> {
            Message request = new Message(PurchaseControllerService.ADD_PURCHASE, ID + " " + ISBN + " " + clientID + " " + purchaseDetails);
            Message response = tcpClient.sendAndReceive(request);
            return null;
        });
    }

    @Override
    public Future<Void> deletePurchase(int ID) {
        return executorService.submit(() -> {
            Message request = new Message(PurchaseControllerService.DELETE_PURCHASE, String.valueOf(ID));
            Message response = tcpClient.sendAndReceive(request);
            return null;
        });
    }

    @Override
    public Future<Void> updatePurchase(int ID, String purchaseDetails) {
        return executorService.submit(() -> {
            Message request = new Message(PurchaseControllerService.ADD_PURCHASE, ID + " " + purchaseDetails);
            Message response = tcpClient.sendAndReceive(request);
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
}
