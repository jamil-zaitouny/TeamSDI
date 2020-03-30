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
    Future<Void> add_client(int ID, String name);
    Future<Void> delete_client(int ID);
    Future<Void> update_client(int ID, String name);
    Future<Client> search_by_id(int ID);
    Future<Set<Client>> filter_by_name(String name);
}
