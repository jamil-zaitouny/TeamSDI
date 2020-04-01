package Common.HandlerServices;

import Model.Client;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface ClientControllerService {
    String PRINT_CLIENTS = "printClients";
    String ADD_CLIENT = "addClient";
    String DELETE_CLIENT = "removeClient";
    String UPDATE_CLIENT = "updateClient";
    String SEARCH_BY_ID = "searchForID";
    String FILTER_BY_Name = "filterByName";

    CompletableFuture<Set<Client>> print_clients();
    CompletableFuture<Void> addClient(int ID, String name);
    CompletableFuture<Void> deleteClient(int ID);
    CompletableFuture<Void> updateClient(int ID, String name);
    CompletableFuture<Client> searchById(int ID);
    CompletableFuture<Set<Client>> filterByName(String name);
    CompletableFuture<Iterable<Client>> sortClientsByName();
}
