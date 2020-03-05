package Controller;

import Model.Exceptions.ValidatorException;
import Model.Purchase;
import Repository.RepositoryInMemory;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PurchaseController
{
    private RepositoryInMemory<Integer, Purchase> repository;

    public PurchaseController(RepositoryInMemory<Integer, Purchase> repository) {
        this.repository = repository;
    }
    public void addPurchase(Purchase purchase) throws ValidatorException
    {
        this.repository.add(purchase);
    }

    public Set<Purchase> getAllPurchases()
    {
        return StreamSupport.stream(this.repository.findAll().spliterator(), false).collect(Collectors.toSet());
    }
}
