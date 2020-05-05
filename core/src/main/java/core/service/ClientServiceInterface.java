package core.service;

import core.model.Client;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ClientServiceInterface {

    public void addClient(Client client) ;
    public void deleteClient(Integer id);
    public void updateClient(Client client);
    public Client searchById(Integer id);
    public Optional<Client> findOne(int ClientID);
    public List<Client> getAllClients();
    public List<Client> filterByName(String name);
}
