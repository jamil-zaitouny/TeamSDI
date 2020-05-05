package client.ui;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Console extends DefaultConsole{

    public static final String URL = "http://localhost:8080/api";
    private RestTemplate restTemplate;

    private static final int BooksOption = 1;
    private static final int ClientsOption = 2;
    private static final int PurchasesOption = 3;

    public Console(RestTemplate restTemplate) {
        this.restTemplate=restTemplate;
    }

    @Override
    public int dealChoice(int choice) {
        switch (choice) {
            case BooksOption:
                dealBooks();
                break;
            case ClientsOption:
                dealClients();
                break;
            case PurchasesOption:
                dealPurchases();
                break;
            case ExitOption:
                return -1;
            default:
                break;
        }
        return 0;
    }

    private void dealClients() {
        ClientConsole clientConsole = new ClientConsole(restTemplate);
        clientConsole.run();
    }

    private void dealBooks() {
        BookConsole bookConsole = new BookConsole(restTemplate);
        bookConsole.run();
    }

    private void dealPurchases()
    {
        PurchaseConsole purchaseConsole=new PurchaseConsole(restTemplate);
        purchaseConsole.run();
    }

    @Override
    public void displayMenu() {
        System.out.println("Options: ");
        System.out.println("\t1.Books ");
        System.out.println("\t2.Clients ");
        System.out.println("\t3.Purchases ");
        System.out.println("\t0.Exit ");
        System.out.println("Choose: ");
    }
}
