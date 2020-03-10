package Controller;

import Model.Book;
import Model.Client;
import Model.Exceptions.InvalidClientDetailsException;
import Model.Exceptions.ValidatorException;
import Model.Purchase;
import Repository.FileRepositories.BookFileRepository;
import Repository.FileRepositories.ClientFileRepository;
import Repository.FileRepositories.PurchaseFileRepository;
import Repository.RepositoryInterface;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PurchaseController
{
    private RepositoryInterface<Integer, Purchase> repository;
    private RepositoryInterface<Integer, Client> clients;
    private RepositoryInterface<String, Book> books;

    public PurchaseController(RepositoryInterface<Integer, Purchase> repository, RepositoryInterface<Integer, Client> clients, RepositoryInterface<String, Book> books) {
        this.repository = repository;
        this.clients = clients;
        this.books = books;
    }

    public void addPurchase(Purchase purchase) throws ValidatorException, IOException{
        clients.findOne(purchase.getClientId()).orElseThrow(()->new ValidatorException("Client does not exist!"));
        books.findOne(purchase.getBookId()).orElseThrow(()->new ValidatorException("Book does not exist!"));
        this.repository.add(purchase);
    }

    public Set<Purchase> getAllPurchases()
    {
        return StreamSupport.stream(this.repository.findAll().spliterator(), false).collect(Collectors.toSet());
    }
}
