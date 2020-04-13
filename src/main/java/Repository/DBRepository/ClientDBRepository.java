package Repository.DBRepository;

import Model.Book;
import Model.Client;
import Model.Exceptions.FileException;
import Repository.SortRepository.Sort;
import Repository.SortRepository.SortingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ClientDBRepository implements SortingRepository<Integer, Client> {
    HashMap<Integer, Client> clients;

    @Autowired
    JdbcOperations jdbcOperations;

    @Override
    public Optional<Client> findOne(Integer id) {
        return Optional.ofNullable(clients.get(id));
    }

    private void loadClients() throws FileException {
        String selectClients = "select * from clients";
        clients = new HashMap<>();
        jdbcOperations.query(selectClients, (rs, rowNum) -> {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            Client client = new Client(id, name);
            clients.put(id, client);
            return client;
        });
    }

    public ClientDBRepository() throws FileException {
        //loadClients();
    }

    @Override
    public Iterable<Client> findAll(Sort sort) {
        /*return sort.sort(clients.values().stream()
                .map(client -> (Object) client)
                .collect(Collectors.toList()))
                .stream().map(client -> (Client) client)
                .collect(Collectors.toList());
        */
        return sort.sort(StreamSupport.stream(findAll().spliterator(), false).collect(Collectors.toList()))
                .stream().map(client-> (Client) client)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Client> findAll() {
        String selectClients = "select * from clients";
        return new HashSet<>( jdbcOperations.query(selectClients, (rs, rowNum) -> {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            return new Client(id, name);
        }));
    }


    @Override
    public Optional<Client> add(Client entity) throws FileException {
        System.out.println(entity);
        Optional<Client> previous = Optional.ofNullable(clients.putIfAbsent(entity.getId(), entity));
        previous.ifPresentOrElse(client -> {
        }, () -> {
            String addClients = "insert into clients (id, name) values(?,?)";
            jdbcOperations.update(addClients, entity.getId(), entity.getName());
        });
        return previous;
    }

    @Override
    public Optional<Client> delete(Integer id) {
        Optional<Client> previous = Optional.ofNullable(clients.remove(id));
        previous.ifPresent(client -> {
            String sql = "delete from clients where id=?";
            jdbcOperations.update(sql, id);
        });
        return previous;
    }

    @Override
    public Optional<Client> update(Client entity) throws FileException {
        Optional<Client> savedValue = Optional.ofNullable(clients.computeIfPresent(entity.getId(), (k, v) -> entity));
        savedValue.ifPresent(client -> {
            String sql ="update clients set name=? where id=?";
            jdbcOperations.update(sql, entity.getName(), entity.getId());
        });
        return savedValue;
    }
}
