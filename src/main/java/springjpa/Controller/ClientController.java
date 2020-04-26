package springjpa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springjpa.Model.Client;
import springjpa.Model.Exceptions.ValidatorException;
import springjpa.Model.Validators.IValidator;
import springjpa.Repository.DBRepository.ClientDBRepository;
import springjpa.Repository.SortRepository.Sort;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientController {
    @Autowired
    private ClientDBRepository repository;

    @Autowired
    private IValidator<Client> validator;
    /*public ClientController(SortingRepository<Integer, Client> repository) {
        this.validator = new ClientValidator();
        this.repository = repository;
    }*/

    public void addClient(Integer id, String name) throws ValidatorException, IOException {
        Client client = new Client(id, name);
        validator.validate(client);
        Optional<Client> previous=repository.findById(client.getId());
        previous.ifPresent(s -> {
            throw new ValidatorException("ID already exists.");
        });
        repository.save(client);
    }

    public void deleteClient(Integer id)
    {
        Optional<Client> previous=repository.findById(id);
        previous.orElseThrow(() -> {
            throw new ValidatorException("Could not find client based on ID.");
        });
        repository.deleteById(id);
    }

    @Transactional
    public void updateClient(Integer id, String newName)
    {
        Client newClient = new Client(id, newName);
        repository.findById(newClient.getId())
                .ifPresentOrElse(s -> {
                    s.setName(newClient.getName());
                }, () -> {
                    throw new ValidatorException("Could not find book based on ID.");
                });
    }

    public Client searchById(Integer id)
    {
        return this.repository.findById(id).get();
    }

    public Optional findOne(int ClientID){
        return repository.findById(ClientID);
    }

    public List<Client> getAllClients()
    {
        return repository.findAll();
    }

    public Set<Client> filterByName(String name) {
        List<Client> clients = getAllClients();
        return clients.stream().filter(v->v.getName().contains(name)).collect(Collectors.toSet());
    }
    public Iterable<Client> sortClientsByName() {
        Sort sort=new Sort("name");
        return sort.sort(repository.findAll().stream()
                .map(s -> (Object)s)
                .collect(Collectors.toList()))
                .stream().map(s->(Client)s)
                .collect(Collectors.toList());
    }
}
