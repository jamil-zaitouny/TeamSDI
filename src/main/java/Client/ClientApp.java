package Client;

import Client.service.BookServiceClient;
import Client.service.ClientServiceClient;
import Client.service.PurchaseServiceClient;
import Common.Communication.BookService;
import Common.Communication.ClientService;
import Common.Communication.PurchaseService;
import Ui.Console;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClientApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("Client.config");
        BookServiceClient bookController=new BookServiceClient(context.getBean(BookService.class));
        ClientServiceClient clientController=new ClientServiceClient(context.getBean(ClientService.class));
        PurchaseServiceClient purchaseController=new PurchaseServiceClient(context.getBean(PurchaseService.class));
        Console console=new Console(clientController,bookController,purchaseController);
        console.run();
    }
}
