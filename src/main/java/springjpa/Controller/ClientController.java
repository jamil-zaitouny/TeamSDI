package springjpa.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientDBRepository repository;

    @Autowired
    private IValidator<Client> validator;

    public void addClient(Integer id, String name) throws ValidatorException, IOException {
        Client client = new Client(id, name);
        validator.validate(client);
        Optional<Client> previous=repository.findById(client.getId());
        previous.ifPresent(s -> {
            logger.info("ERROR: Adding failed, id existent: " + id);
            throw new ValidatorException("ID already exists.");
        });
        logger.info("Added new client.");
        repository.save(client);
    }

    public void deleteClient(Integer id)
    {
        Optional<Client> previous=repository.findById(id);
        previous.orElseThrow(() -> {
            logger.info("ERROR: Deleted failed, id non existent: " + id);
            throw new ValidatorException("Could not find client based on ID.");
        });
        logger.info("Deleted client");
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
                    logger.info("ERROR: updating client that doesn't exist: " + id);
                    throw new ValidatorException("Could not find book based on ID.");
                });
    }

    public Client searchById(Integer id)
    {
        logger.info("Search for client: " + id);
        return this.repository.findById(id).get();
    }

    public Optional findOne(int ClientID){
        logger.info("Search for client: " + ClientID);
        return repository.findById(ClientID);
    }

    public List<Client> getAllClients()
    {
        logger.info("Retrieving list of clients");
        return repository.findAll();
    }

    public Set<Client> filterByName(String name) {
        logger.info("Filtering by name: " + name);
        List<Client> clients = getAllClients();
        return clients.stream().filter(v->v.getName().contains(name)).collect(Collectors.toSet());
    }
    public Iterable<Client> sortClientsByName() {
        logger.info("Sorting client by name.");
        Sort sort=new Sort("name");
        return sort.sort(repository.findAll().stream()
                .map(s -> (Object)s)
                .collect(Collectors.toList()))
                .stream().map(s->(Client)s)
                .collect(Collectors.toList());
    }
}
