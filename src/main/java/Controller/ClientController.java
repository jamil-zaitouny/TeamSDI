package Controller;

import Model.Client;
import Model.Exceptions.ValidatorException;
import Model.Validators.ClientValidator;
import Model.Validators.IValidator;
import Repository.RepositoryInMemory;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ClientController {
    private RepositoryInMemory<Integer, Client> repository;
    private IValidator<Client> validator;
    public ClientController(RepositoryInMemory<Integer, Client> repository) {
        this.validator = new ClientValidator();
        this.repository = repository;
    }

    public void addClient(Client client) throws ValidatorException
    {
        validator.validate(client);
        this.repository.add(client);
    }

    public Set<Client> getAllClients()
    {
        return StreamSupport.stream(this.repository.findAll().spliterator(), false).collect(Collectors.toSet());
    }
}
