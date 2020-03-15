package Controller;

import Model.Book;
import Model.Exceptions.ValidatorException;
import Model.Purchase;
import Model.Validators.PurchaseValidator;
import Repository.RepositoryInterface;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PurchaseController
{
    private RepositoryInterface<Integer, Purchase> repository;
    private ClientController clients;
    private BookController books;
    private PurchaseValidator validator;
    public PurchaseController(RepositoryInterface<Integer, Purchase> repository,ClientController clients,  BookController books) {
        this.repository = repository;
        this.clients = clients;
        this.books = books;
        validator = new PurchaseValidator();
    }

    public void addPurchase(Purchase purchase) throws Throwable {
        clients.findOne(purchase.getClientId()).orElseThrow(()->new ValidatorException("Client does not exist!"));
        books.findOne(purchase.getBookId()).orElseThrow(()->new ValidatorException("Book does not exist!"));
        validator.validate(purchase);
        this.repository.add(purchase);
    }

    public Set<Purchase> getAllPurchases()
    {
        return StreamSupport.stream(this.repository.findAll().spliterator(), false).collect(Collectors.toSet());
    }

    public void deletePurchase(Integer id)
    {
        this.repository.delete(id);
    }

    public void updatePurchase(Integer id, String newDetails)
    {
        Purchase newPurchase = new Purchase(id,this.repository.findOne(id).get().getBookId(),this.repository.findOne(id).get().getClientId(),newDetails);
        this.validator.validate(newPurchase);
        this.repository.update(newPurchase);
    }

    public Optional findOne(int purchaseID){
        return repository.findOne(purchaseID);
    }

}
