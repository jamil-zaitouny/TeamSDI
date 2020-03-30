package Ui;

import Common.HandlerServices.PurchaseControllerService;
import Controller.PurchaseController;
import Model.Purchase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

public class PurchaseConsole extends DefaultConsole {
    private PurchaseControllerService purchaseController;

    private static final int PrintPurchasesOption = 1;
    private static final int AddPurchaseOption = 2;
    private static final int DeletePurchaseOption = 3;
    private static final int UpdatePurchaseOption = 4;
    private static final int TopThreeClientsOption = 5;
    private static final int TopThreeBooksOption = 6;
    private static final int BooksPerGenreOption = 7;
    private static final int BestClientOption = 8;

    PurchaseConsole(PurchaseControllerService purchaseController) {
        this.purchaseController = purchaseController;
    }

    @Override
    protected int dealChoice(int choice) throws IOException, ExecutionException, InterruptedException {
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
            case BooksPerGenreOption:
                printBookPerGenre();
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

    private void printBestClient() throws IOException, ExecutionException, InterruptedException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("genre: ");
        String description = bufferRead.readLine();
        this.purchaseController.clientsWithMostPurchases().get().forEach(System.out::print);
    }

    private void printBookPerGenre() throws ExecutionException, InterruptedException {
        this.purchaseController.booksWithHighestPurchaseCountPerGenre().get().forEach(System.out::println);
    }

    private void printTopThreeBooks() throws ExecutionException, InterruptedException {
        this.purchaseController.booksWithHighestPurchaseCount().get().forEach(System.out::println);
    }

    private void printTopThreeClients() throws ExecutionException, InterruptedException {
        this.purchaseController.clientsWithMostPurchases().get().forEach(System.out::println);
    }

    private void addPurchase() throws Throwable {

        this.purchaseController.addPurchase(readPurchase());
    }

    private void printPurchases() throws ExecutionException, InterruptedException {
        this.purchaseController.sortPurchasesByDescription().get().forEach(System.out::println);
    }

    private void updatePurchase() throws IOException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Id: ");
        int id = Integer.parseInt(bufferRead.readLine());
        System.out.println("New description: ");
        String description = bufferRead.readLine();

        this.purchaseController.updatePurchase(id, description);
    }

    private void deletePurchase() throws IOException {
        System.out.println("Id: ");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(bufferedReader.readLine());

        this.purchaseController.deletePurchase(id);
    }


    private Purchase readPurchase() {
        System.out.println("Read Purchase {Id, Book ISBN, Client Id, Purchase details}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            int id = Integer.parseInt(bufferRead.readLine());
            String book = bufferRead.readLine();
            int client = Integer.parseInt(bufferRead.readLine());
            String details = bufferRead.readLine();

            return new Purchase(id, book, client, details);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
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
        System.out.println("\t7.Books sold for every genre");
        System.out.println("\t8.The client that bought the most books with the same genre");
        System.out.println("\t0.Go back");
    }
}
