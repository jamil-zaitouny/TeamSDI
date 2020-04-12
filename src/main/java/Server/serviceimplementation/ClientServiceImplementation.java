package Server.serviceimplementation;

import Common.Communication.ClientService;
import Controller.ClientController;
import Model.Client;
import Repository.SortRepository.SortingRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@Component
public class ClientServiceImplementation implements ClientService {
    private SortingRepository<Integer, Client> clientRepository;
    private ClientController clientController;

    public ClientServiceImplementation(SortingRepository<Integer, Client> clientRepository){
        this.clientRepository = clientRepository;
        this.clientController = new ClientController(clientRepository);
    }

    @Override
    public Set<Client> print_clients() {
        return this.clientController.getAllClients();
    }

    @Override
    public void addClient(int ID, String name) {
        try {
            this.clientController.addClient(ID, name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteClient(int ID) {
        this.clientController.deleteClient(ID);
    }

    @Override
    public void updateClient(int ID, String name) {
        this.clientController.updateClient(ID, name);
    }

    @Override
    public Client searchById(int ID) {
        return this.clientController.searchById(ID);
    }

    @Override
    public Set<Client> filterByName(String name) {
        return this.clientController.filterByName(name);
    }

    @Override
    public Iterable<Client> sortClientsByName() {
        return this.clientController.sortClientsByName();
    }

    @Override
    public Optional findOne(int clientID) {
        return this.clientController.findOne(clientID);
    }

    @Override
    public Set<Client> getAllClients() {
        return this.clientController.getAllClients();
    }
}
