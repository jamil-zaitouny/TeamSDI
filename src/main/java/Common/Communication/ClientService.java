package Common.Communication;

import Model.Client;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Future;

public interface ClientService {
    Set<Client> print_clients();
    void addClient(int ID, String name);
    void deleteClient(int ID);
    void updateClient(int ID, String name);
    Client searchById(int ID);
    Set<Client> filterByName(String name);
    Iterable<Client> sortClientsByName();
    Optional findOne(int clientID);
    Set<Client> getAllClients();
}
