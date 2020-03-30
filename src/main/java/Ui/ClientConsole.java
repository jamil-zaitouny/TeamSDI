package Ui;

import Common.HandlerServices.ClientControllerService;
import Common.HandlerServices.PurchaseControllerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ClientConsole extends DefaultConsole {
    private ClientControllerService clientController;
    private PurchaseControllerService purchaseController;

    private static final int PrintClientsOption = 1;
    private static final int AddClientOption = 2;
    private static final int DeleteClientOption = 3;
    private static final int UpdateClientOption = 4;
    private static final int SearchByIdClientOption = 5;
    private static final int FilterByName = 6;

    ClientConsole(ClientControllerService clientController,PurchaseControllerService purchaseControllercontrollerr) {
        this.clientController = clientController;
        this.purchaseController =purchaseControllercontrollerr;
    }

    @Override
    protected int dealChoice(int choice) throws IOException {
        switch (choice) {
            case PrintClientsOption:
                printClients();
                break;
            case AddClientOption:
                addClient();
                break;
            case DeleteClientOption:
                deleteClient();
                break;
            case UpdateClientOption:
                updateClient();
                break;
            case SearchByIdClientOption:
                searchByIdClient();
                break;
            case FilterByName:
                filterByName();
                break;
            case ExitOption:
                return -1;
            default:
                System.out.println("Wrong option! Try again!");
                break;
        }
        return 0;
    }

    private void filterByName() throws IOException, ExecutionException, InterruptedException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Name: ");
        String name = bufferRead.readLine();

       this.clientController.filterByName(name).get().forEach(System.out::println);
    }

    private void searchByIdClient() throws IOException, ExecutionException, InterruptedException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Id: ");
        int id = Integer.parseInt(bufferRead.readLine());

        System.out.println(this.clientController.searchById(id).get());
    }

    private void updateClient() throws IOException{
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Id: ");
        int id = Integer.parseInt(bufferRead.readLine());
        System.out.println("New name: ");
        String name = bufferRead.readLine();

        this.clientController.updateClient(id, name);
    }

    private void deleteClient() throws IOException {
        System.out.println("Id: ");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(bufferedReader.readLine());

        this.purchaseController.deleteAllPurchasesForClient(id);
        this.clientController.deleteClient(id);
    }

    private void addClient() throws IOException {
        System.out.println("Read Client {Id, Client}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(bufferRead.readLine());
        String name = bufferRead.readLine();
        this.clientController.addClient(id, name);
    }

    private void printClients() {
        this.clientController.sortClientsByName().forEach(System.out::println);
    }

    @Override
    protected void displayMenu() {
        System.out.println("Options: ");
        System.out.println("\t1.Print clients");
        System.out.println("\t2.Add client");
        System.out.println("\t3.Delete client");
        System.out.println("\t4.Update client");
        System.out.println("\t5.Search by Id");
        System.out.println("\t6.Filter by name");
        System.out.println("\t0.Go back");
    }
}
