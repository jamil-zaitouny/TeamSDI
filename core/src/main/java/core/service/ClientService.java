package core.service;

import core.model.Client;
import core.repository.ClientDBRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import core.model.Exceptions.ValidatorException;
import core.model.Validators.IValidator;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientService implements ClientServiceInterface {

    final Logger logger = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private ClientDBRepository repository;

    @Autowired
    private IValidator<Client> validator;

    public void addClient(Client client) throws ValidatorException{
        validator.validate(client);
        Optional<Client> previous=repository.findById(client.getId());
        previous.ifPresent(s -> {
            logger.info("ERROR: Adding failed, id existent: " );
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

    //@Transactional
    public void updateClient(Client newClient)
    {
        //Client newClient = new Client(id, newName);
        /*repository.findById(newClient.getId())
                .ifPresentOrElse(s -> {
                    s.setName(newClient.getName());
                }, () -> {
                    logger.info("ERROR: updating client that doesn't exist: ");
                    throw new ValidatorException("Could not find book based on ID.");
                });*/
        this.deleteClient(newClient.getId());
        this.addClient(newClient);
        logger.info("Updating an existent client: ");
    }

    public Client searchById(Integer id)
    {
        logger.info("Search for client: " + id);
        return this.repository.findById(id).get();
    }

    public Optional<Client> findOne(int ClientID){
        logger.info("Search for client: " + ClientID);
        return repository.findById(ClientID);
    }

    public List<Client> getAllClients()
    {
        logger.info("Retrieving list of clients");
        return repository.findAll();
    }

    public List<Client> filterByName(String name) {
        logger.info("Filtering by name: " + name);
        List<Client> clients = getAllClients();
        return clients.stream().filter(v->v.getName().contains(name)).collect(Collectors.toList());
    }
    /*public Iterable<Client> sortClientsByName() {
        logger.info("Sorting client by name.");
        Sort sort=new Sort("name");
        return sort.sort(repository.findAll().stream()
                .map(s -> (Object)s)
                .collect(Collectors.toList()))
                .stream().map(s->(Client)s)
                .collect(Collectors.toList());
    }*/
}
