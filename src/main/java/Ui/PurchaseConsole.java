package Ui;

import Controller.PurchaseController;
import Model.Purchase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PurchaseConsole extends DefaultConsole
{
    private PurchaseController purchaseController;

    private static final int PrintPurchasesOption = 1;
    private static final int AddPurchaseOption = 2;

    public PurchaseConsole(PurchaseController purchaseController) {
        this.purchaseController = purchaseController;
    }

    @Override
    protected int dealChoice(int choice) {
        switch (choice) {
            case PrintPurchasesOption:
                printPurchases();
                break;
            case AddPurchaseOption:
                addPurchase();
                break;
            case ExitOption:
                return -1;
            default:
                System.out.println("Wrong option! Try again!");
                break;
        }
        return 0;
    }

    private void addPurchase() {
        this.purchaseController.addPurchase(readPurchase());
    }

    private void printPurchases() {
        this.purchaseController.getAllPurchases().forEach(System.out::println);
    }


    private Purchase readPurchase() {
        System.out.println("Read Purchase {Id, Book ISBN, CLient Id}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            int id = Integer.parseInt(bufferRead.readLine());
            String book = bufferRead.readLine();
            int client=Integer.parseInt(bufferRead.readLine());

            return new Purchase(id,book,client);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void displayMenu() {
        System.out.println("Options: ");
        System.out.println("\t1.Print purchases");
        System.out.println("\t2.Add purchase");
        System.out.println("\t0.Go back");
    }
}
