package Server.configuration;

import Common.Communication.PurchaseService;
import Controller.BookController;
import Controller.ClientController;
import Model.Book;
import Model.Client;
import Model.Purchase;
import Repository.DBRepository.BookDBRepository;
import Repository.DBRepository.ClientDBRepository;
import Repository.DBRepository.PurchaseDBRepository;
import Repository.SortRepository.SortingRepository;
import Server.serviceimplementation.PurchaseServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
@ComponentScan("Repository.DBRepository")
@ComponentScan("Controller")
public class PurchaseServiceConfiguration {
    @Autowired
    private PurchaseDBRepository purchaseRepository;
    @Autowired
    private ClientDBRepository clientRepository;
    @Autowired
    private BookDBRepository bookRepository;
    @Autowired
    private ClientController clientController;
    @Autowired
    private BookController bookController;

    @Bean
    RmiServiceExporter rmiPurchaseServiceExporter(){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("PurchaseController");
        rmiServiceExporter.setServiceInterface(PurchaseService.class);
        rmiServiceExporter.setService(purchaseService());
        rmiServiceExporter.setRegistryPort(1100);
        return rmiServiceExporter;
    }

    @Bean
    public PurchaseService purchaseService(){
        SortingRepository<Integer, Purchase> purchaseSortingRepository = purchaseRepository;
        SortingRepository<Integer, Client> clientSortingRepository = clientRepository;
        SortingRepository<String, Book> bookSortingRepository = bookRepository;
        this.clientController = new ClientController(clientSortingRepository);
        this.bookController = new BookController(bookSortingRepository);
        return new PurchaseServiceImplementation( bookController,clientController, purchaseSortingRepository);
    }
}
