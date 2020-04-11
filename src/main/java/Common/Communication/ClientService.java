package Common.Communication;

import Model.Client;

import java.util.Set;
import java.util.concurrent.Future;

public interface ClientService {
    Future<Set<Client>> print_clients();
    Future<Void> addClient(int ID, String name);
    Future<Void> deleteClient(int ID);
    Future<Void> updateClient(int ID, String name);
    Future<Client> searchById(int ID);
    Future<Set<Client>> filterByName(String name);
    Future<Iterable<Client>> sortClientsByName();
}
