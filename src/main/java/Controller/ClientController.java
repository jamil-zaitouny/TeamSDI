package Controller;

import Model.Client;
import Model.Exceptions.ValidatorException;
import Repository.ClientRepositoryInMemory;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ClientController {
    private ClientRepositoryInMemory<Integer, Client> repository;

    public ClientController(ClientRepositoryInMemory<Integer, Client> repository) {
        this.repository = repository;
    }

    public void addClient(Client client) throws ValidatorException
    {
        this.repository.add(client);
    }

    public Set<Client> getAllClients()
    {
        return StreamSupport.stream(this.repository.findAll().spliterator(), false).collect(Collectors.toSet());
    }
}
