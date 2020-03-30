package Client;

import Client.TCP.TCPClient;
import Client.service.BookControllerHandler;
import Client.service.ClientControllerHandler;
import Client.service.PurchaseControllerHandler;
import Common.HandlerServices.BookControllerService;
import Common.HandlerServices.ClientControllerService;
import Common.HandlerServices.PurchaseControllerService;
import Ui.Console;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        TCPClient client = new TCPClient();

        ClientControllerService clientControllerHandler = new ClientControllerHandler(executorService,client);
        BookControllerService bookControllerHandler = new BookControllerHandler(executorService, client);
        PurchaseControllerService purchaseControllerHandler = new PurchaseControllerHandler(executorService, client);

        Console console = new Console(clientControllerHandler, bookControllerHandler, purchaseControllerHandler);
        console.run();

        executorService.shutdown();
    }
}
