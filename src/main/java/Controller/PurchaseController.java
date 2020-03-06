package Controller;

import Model.Book;
import Model.Client;
import Model.Exceptions.InvalidClientDetailsException;
import Model.Exceptions.ValidatorException;
import Model.Purchase;
import Repository.RepositoryInMemory;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PurchaseController
{
    private RepositoryInMemory<Integer, Purchase> repository;
    private RepositoryInMemory<Integer, Client> clients;
    private RepositoryInMemory<String, Book> books;

    public PurchaseController(RepositoryInMemory<Integer, Purchase> repository, RepositoryInMemory<Integer, Client> clients, RepositoryInMemory<String, Book> books) {
        this.repository = repository;
        this.clients = clients;
        this.books = books;
    }

    public void addPurchase(Purchase purchase) throws ValidatorException
    {
        clients.findOne(purchase.getClientId()).orElseThrow(()->new ValidatorException("Inexisting client"));
        books.findOne(purchase.getBookId()).orElseThrow(()->new ValidatorException("Inexisting book"));
        this.repository.add(purchase);
    }

    public Set<Purchase> getAllPurchases()
    {
        return StreamSupport.stream(this.repository.findAll().spliterator(), false).collect(Collectors.toSet());
    }
}
