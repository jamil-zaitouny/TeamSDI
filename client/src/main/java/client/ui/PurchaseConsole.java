package client.ui;

import core.model.Purchase;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import web.dto.PurchaseDto;
import web.dto.PurchasesDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class PurchaseConsole extends DefaultConsole {
    public static final String URL = "http://localhost:8080/api";
    private RestTemplate restTemplate;

    private static final int PrintPurchasesOption = 1;
    private static final int AddPurchaseOption = 2;
    private static final int DeletePurchaseOption = 3;
    private static final int UpdatePurchaseOption = 4;
    private static final int TopThreeClientsOption = 5;
    private static final int TopThreeBooksOption = 6;
    private static final int BestClientOption = 7;

    PurchaseConsole(RestTemplate restTemplate) {
        this.restTemplate=restTemplate;
    }

    @Override
    protected int dealChoice(int choice) throws IOException {
        switch (choice) {
            case PrintPurchasesOption:
                printPurchases();
                break;
            case AddPurchaseOption:
                try {
                    addPurchase();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                break;
            case DeletePurchaseOption:
                deletePurchase();
                break;
            case UpdatePurchaseOption:
                updatePurchase();
                break;
            case TopThreeClientsOption:
                printTopThreeClients();
                break;
            case TopThreeBooksOption:
                printTopThreeBooks();
                break;
            case BestClientOption:
                printBestClient();
                break;
            case ExitOption:
                return -1;
            default:
                System.out.println("Wrong option! Try again!");
                break;
        }
        return 0;
    }

    private void printBestClient() throws IOException{
        /*BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("genre: ");
        String description = bufferRead.readLine();

        System.out.println(this.purchaseController.getClientMostBooksGenre(description));*/
    }

    private void printTopThreeBooks() {
        //PurchasesDto allPurchases=restTemplate.getForObject(URL+"/purchases", PurchasesDto.class);
        //List<PurchaseDto> books=allPurchases.
        //books.forEach(System.out::println);

        //this.purchaseController.getTopThreeBooksBought().forEach(System.out::println);
    }

    private void printTopThreeClients() {
        /*List<String> topClients=restTemplate.execute(URL+"/purchases/topclients",printTopThreeClients(),List.class);
        List<PurchaseDto> books=allPurchases.
        topClients.forEach(System.out::println);
        //this.purchaseController.getTopThreeClientsMostBooks().forEach(System.out::println);*/
    }

    private void addPurchase() throws Throwable {

        System.out.println("Read Purchase {Id, Book ISBN, Client Id, Purchase details}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            int id = Integer.parseInt(bufferRead.readLine());
            String book = bufferRead.readLine();
            int client = Integer.parseInt(bufferRead.readLine());
            String details = bufferRead.readLine();

            PurchaseDto purchaseDto= new PurchaseDto(book,client,details);
            purchaseDto.setId(id);

            try {
                restTemplate.postForObject(URL+"/purchases" , purchaseDto, PurchaseDto.class);
            }
            catch (RestClientException e)
            {
                System.out.println("Can't add");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void printPurchases() {
        PurchasesDto allPurchases=restTemplate.getForObject(URL+"/purchases", PurchasesDto.class);
        List<PurchaseDto> books=allPurchases.getPurchases();
        books.forEach(System.out::println);
    }

    private void updatePurchase() throws IOException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Id: ");
        int id = Integer.parseInt(bufferRead.readLine());
        System.out.println("Book: ");
        String book = bufferRead.readLine();
        System.out.println("Client: ");
        int client = Integer.parseInt(bufferRead.readLine());
        System.out.println("New description: ");
        String description = bufferRead.readLine();

        PurchaseDto purchaseDto= new PurchaseDto(book,client,description);
        purchaseDto.setId(id);
        System.out.println(purchaseDto);
        try {
            restTemplate.put(URL+"/purchases/{id}" , purchaseDto, id);
        }
        catch (RestClientException e)
        {
            System.out.println("Can't update");
        }
    }

    private void deletePurchase() throws IOException {
        System.out.println("Id: ");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(bufferedReader.readLine());

        restTemplate.delete(URL+"/purchases/{id}",id);
        //this.purchaseController.deletePurchase(id);
    }

    @Override
    protected void displayMenu() {
        System.out.println("Options: ");
        System.out.println("\t1.Print purchases");
        System.out.println("\t2.Add purchase");
        System.out.println("\t3.Delete purchase");
        System.out.println("\t4.Update purchase");
        System.out.println("\t5.Top three clients that bought the most books");
        System.out.println("\t6.Top three books that were bought the most");
        System.out.println("\t7.The client that bought the most books with the same genre");
        System.out.println("\t0.Go back");
    }
}
