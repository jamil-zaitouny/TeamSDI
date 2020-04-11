package Client.service;
import Common.Communication.ClientService;
import Model.Client;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ClientServiceClient {

    ClientService clientService;

    public ClientServiceClient(ClientService clientService) {
        this.clientService = clientService;
    }

    public CompletableFuture<Set<Client>> print_clients() {
        return CompletableFuture.supplyAsync(()->clientService.print_clients());
    }

    public CompletableFuture<Void> addClient(int ID, String name) {
        return CompletableFuture.runAsync(()->clientService.addClient(ID,name));
    }

    public CompletableFuture<Void> deleteClient(int ID) {
        return CompletableFuture.runAsync(()->clientService.deleteClient(ID));
    }

    public CompletableFuture<Void> updateClient(int ID, String name) {
        return CompletableFuture.runAsync(()->clientService.updateClient(ID,name));
    }

    public CompletableFuture<Client> searchById(int ID) {
        return CompletableFuture.supplyAsync(()->clientService.searchById(ID));
    }

    public CompletableFuture<Set<Client>> filterByName(String name) {
        return CompletableFuture.supplyAsync(()->clientService.filterByName(name));
    }

    public CompletableFuture<Iterable<Client>> sortClientsByName() {
        return CompletableFuture.supplyAsync(()->clientService.sortClientsByName());
    }

    public CompletableFuture<Optional> findOne(int clientID) {
        return CompletableFuture.supplyAsync(()->clientService.findOne(clientID));
    }

    public CompletableFuture<Set<Client>> getAllClients() {
        return CompletableFuture.supplyAsync(()->clientService.getAllClients());
    }
}
