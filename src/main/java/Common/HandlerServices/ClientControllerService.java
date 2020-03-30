package Common.HandlerServices;

import Model.Client;

import java.util.Set;
import java.util.concurrent.Future;

public interface ClientControllerService {
    String PRINT_CLIENTS = "printClients";
    String ADD_CLIENT = "addClient";
    String DELETE_CLIENT = "removeClient";
    String UPDATE_CLIENT = "updateClient";
    String SEARCH_BY_ID = "searchForID";
    String FILTER_BY_Name = "filterByName";

    Future<Set<Client>> print_clients();
    Future<Void> addClient(int ID, String name);
    Future<Void> deleteClient(int ID);
    Future<Void> updateClient(int ID, String name);
    Future<Client> searchById(int ID);
    Future<Set<Client>> filterByName(String name);
    Future<Iterable<Client>> sortClientsByName();
}
