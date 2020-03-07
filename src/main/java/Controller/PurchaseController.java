package Controller;

import Model.Book;
import Model.Client;
import Model.Exceptions.InvalidPurchaseDetailException;
import Model.Exceptions.ValidatorException;
import Model.Purchase;
import Model.Validators.IValidator;
import Model.Validators.PurchaseValidator;
import Repository.RepositoryInMemory;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PurchaseController
{
    private RepositoryInMemory<Integer, Purchase> repository;
    private RepositoryInMemory<Integer, Client> clients;
    private RepositoryInMemory<String, Book> books;
    private IValidator<Purchase> validator;

    public PurchaseController(RepositoryInMemory<Integer, Purchase> repository, RepositoryInMemory<Integer, Client> clients, RepositoryInMemory<String, Book> books) {
        validator = new PurchaseValidator();
        this.repository = repository;
        this.clients = clients;
        this.books = books;
    }

    public void addPurchase(Purchase purchase) throws ValidatorException
    {

        books.findOne(purchase.getBookId()).orElseThrow(() -> new InvalidPurchaseDetailException("Book not found"));
        clients.findOne(purchase.getClientId()).orElseThrow(() -> new InvalidPurchaseDetailException("Client doesn't exist"));
        this.repository.add(purchase);
    }

    public Set<Purchase> getAllPurchases()
    {
        return StreamSupport.stream(this.repository.findAll().spliterator(), false).collect(Collectors.toSet());
    }
}
