package Server.service;

import Controller.ClientController;
import Common.HandlerServices.ClientControllerService;
import Model.Client;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ClientControllerHandler implements ClientControllerService {
    private ExecutorService executorService;
    private ClientController clientController;
    public ClientControllerHandler( ExecutorService executorService,ClientController clientController){
        this.clientController = clientController;
        this.executorService = executorService;
    }

    @Override
    public Future<Set<Client>> print_clients() {
        return executorService.submit(()->clientController.getAllClients());
    }

    @Override
    public Future<Void> add_client(int ID, String name) {
        return executorService.submit(()->{
            clientController.addClient(ID, name);
            return null;
        });
    }

    @Override
    public Future<Void> delete_client(int ID) {
        return executorService.submit(()->{
            clientController.deleteClient(ID);
            return null;
        });
    }

    @Override
    public Future<Void> update_client(int ID, String name) {
        return executorService.submit(()->{
            clientController.updateClient(ID, name);
            return null;
        });
    }

    @Override
    public Future<Client> search_by_id(int ID) {
        return executorService.submit(()->clientController.searchById(ID));
    }

    @Override
    public Future<Set<Client>> filter_by_name(String name) {
        return executorService.submit(()->clientController.filterByName(name));
    }
}
