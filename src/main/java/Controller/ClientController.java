package Controller;

import Model.Client;
import Model.Exceptions.ValidatorException;
import Model.Purchase;
import Model.Validators.ClientValidator;
import Model.Validators.IValidator;
import Repository.RepositoryInterface;
import Repository.SortRepository.Sort;
import Repository.SortRepository.SortingRepository;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ClientController {
    private SortingRepository<Integer, Client> repository;

    private IValidator<Client> validator;
    public ClientController(SortingRepository<Integer, Client> repository) {
        this.validator = new ClientValidator();
        this.repository = repository;
    }

    public void addClient(Integer id, String name) throws ValidatorException, IOException {
        Client client = new Client(id, name);
        validator.validate(client);
        this.repository.add(client);
    }

    public void deleteClient(Integer id)
    {
        this.repository.delete(id);
    }

    public void updateClient(Integer id, String newName)
    {
        Client newClient = new Client(id, newName);
        this.validator.validate(newClient);
        this.repository.update(newClient);
    }

    public Client searchById(Integer id)
    {
        return this.repository.findOne(id).get();
    }

    public Optional findOne(int ClientID){
        return repository.findOne(ClientID);
    }

    public Set<Client> getAllClients()
    {
        return StreamSupport.stream(sortClientsByName().spliterator(), false).collect(Collectors.toSet());
    }

    public Set<Client> filterByName(String name) {
        Set<Client> clients = getAllClients();
        return clients.stream().filter(v->v.getName().contains(name)).collect(Collectors.toSet());
    }
    public Iterable<Client> sortClientsByName() {
        Sort sort=new Sort(Sort.Direction.ASC,"name");
        return repository.findAll(sort);
    }
}
