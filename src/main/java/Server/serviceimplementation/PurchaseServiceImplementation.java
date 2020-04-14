package Server.serviceimplementation;

import Common.Communication.PurchaseService;
import Controller.BookController;
import Controller.ClientController;
import Controller.PurchaseController;
import Model.Purchase;
import Repository.SortRepository.SortingRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class PurchaseServiceImplementation implements PurchaseService {

    private SortingRepository<Integer, Purchase> purchaseSortingRepository;
    private ClientController clientController;
    private BookController bookController;
    private PurchaseController purchaseController;
    public PurchaseServiceImplementation(BookController bookController, ClientController clientController, SortingRepository<Integer, Purchase> purchaseSortingRepository){
        this.purchaseSortingRepository = purchaseSortingRepository;
        this.bookController = bookController;
        this.clientController = clientController;
        this.purchaseController = new PurchaseController(purchaseSortingRepository, clientController, bookController);
    }

    @Override
    public Set<Purchase> printPurchases() {
        return purchaseController.getAllPurchases();
    }

    @Override
    public void addPurchase(Purchase purchase) {
        try {
            this.purchaseController.addPurchase(purchase);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void deletePurchase(int ID) {
        this.purchaseController.deletePurchase(ID);
    }

    @Override
    public void updatePurchase(int ID, String purchaseDetails) {
        this.purchaseController.updatePurchase(ID, purchaseDetails);
    }

    @Override
    public Set<String> clientsWithMostPurchases() {
        return this.purchaseController.getTopThreeClientsMostBooks();
    }

    @Override
    public Set<String> booksWithHighestPurchaseCount() {
        return this.purchaseController.getTopThreeBooksBought();
    }

    @Override
    public List<String> booksWithHighestPurchaseCountPerGenre() {
        return this.purchaseController.getBooksBoughtPerGenre();
    }

    @Override
    public void deleteAllPurchasesForBook(String ibsn) {
        this.purchaseController.deleteAllPurchasesForBook(ibsn);
    }

    @Override
    public void deleteAllPurchasesForClient(int id) {
        this.purchaseController.deleteAllPurchasesForClient(id);
    }

    @Override
    public Iterable<Purchase> sortPurchasesByDescription() {
        return this.purchaseController.sortPurchasesByDescription();
    }

    @Override
    public Optional findOne(int purchaseID) {
        return this.purchaseController.findOne(purchaseID);
    }

    @Override
    public Set<Purchase> getAllPurchases() {
        return this.purchaseController.getAllPurchases();
    }
}
