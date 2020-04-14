package Server;

import Model.Purchase;
import Repository.DBRepository.BookDBRepository;
import Repository.DBRepository.ClientDBRepository;
import Repository.DBRepository.PurchaseDBRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class ServerRMIApp {
    public static void main(String args[]){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("Server.configuration");
        BookDBRepository bookrepo =(BookDBRepository) context.getBean("bookDBRepository");
        bookrepo.loadBooks();
        ClientDBRepository clientrepo =(ClientDBRepository) context.getBean("clientDBRepository");
        clientrepo.loadClients();
        PurchaseDBRepository purchaserepo =(PurchaseDBRepository) context.getBean("purchaseDBRepository");
        purchaserepo.loadPurchases();
        System.out.println("Server started");

    }
}
