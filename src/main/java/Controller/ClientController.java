package Controller;

import Model.Client;
import Model.Exceptions.ValidatorException;
import Repository.RepositoryInMemory;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ClientController {
    private RepositoryInMemory<Integer, Client> repository;

    public ClientController(RepositoryInMemory<Integer, Client> repository) {
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
