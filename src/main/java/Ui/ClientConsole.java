package Ui;

import Controller.ClientController;
import Model.Client;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientConsole extends DefaultConsole {
    private ClientController clientController;

    private static final int PrintClientsOption = 1;
    private static final int AddClientOption = 2;

    ClientConsole(ClientController clientController) {
        this.clientController = clientController;
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
            case ExitOption:
                return -1;
            default:
                System.out.println("Wrong option! Try again!");
                break;
        }
        return 0;
    }

    private void addClient() throws IOException {
        this.clientController.addClient(readClient());
    }

    private void printClients() {
        this.clientController.getAllClients().forEach(System.out::println);
    }


    private Client readClient() {
        System.out.println("Read Client {Id, Client}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            int id = Integer.parseInt(bufferRead.readLine());
            String author = bufferRead.readLine();

            return new Client(id, author);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void displayMenu() {
        System.out.println("Options: ");
        System.out.println("\t1.Print clients");
        System.out.println("\t2.Add client");
        System.out.println("\t0.Go back");
    }
}
