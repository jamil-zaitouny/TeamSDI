package Repository.DBRepository;

import Model.Client;
import Model.Exceptions.FileException;
import Repository.SortRepository.Sort;
import Repository.SortRepository.SortingRepository;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDBRepository implements SortingRepository<Integer, Client>
{
    HashMap<Integer,Client> clients;
    Connection connection;


    @Override
    public Optional<Client> findOne(Integer id) {
        return Optional.ofNullable(clients.get(id));
    }

    private void loadClients() throws FileException {
        try {
            String selectClients="select * from clients";
            PreparedStatement selectClientsStatement=connection.prepareStatement(selectClients);
            ResultSet clientsSet=selectClientsStatement.executeQuery();
            System.out.println(clientsSet);
            while(clientsSet.next()) {
                int id=clientsSet.getInt("id");
                String name=clientsSet.getString("name");
                clients.put(id,new Client(id,name));
            }
        } catch (SQLException e) {
            throw new FileException("There was some problem with the database!");
        }
    }

    public ClientDBRepository() throws FileException {
        try {
            clients=new HashMap<>();
            //Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost/bookshop", "postgres", "admin");
            //connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bookshop?user=postgres&password=admin");
        } catch (SQLException e) {
            throw new FileException("Could not connect to the database!");
        }
        try {
            loadClients();
        } catch (FileException e) {
            throw e;
        }
    }

    @Override
    public Iterable<Client> findAll(Sort sort) {
        return sort.sort(clients.values().stream()
                .map(client -> (Object) client)
                .collect(Collectors.toList()))
                .stream().map(client -> (Client) client)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Client> findAll() {
        Set<Client> allEntities = new HashSet<>();

        try {
            String selectClients="select * from clients";
            PreparedStatement selectClientsStatement=connection.prepareStatement(selectClients);
            ResultSet clientsSet=selectClientsStatement.executeQuery();
            System.out.println(clientsSet);
            while(clientsSet.next()) {
                int id=clientsSet.getInt("id");
                String name=clientsSet.getString("name");
                allEntities.add(new Client(id,name));
            }
        } catch (SQLException e) {
            throw new FileException("There was some problem with the database!");
        }
        return allEntities;
    }


    @Override
    public Optional<Client> add(Client entity) throws FileException {
        System.out.println(entity);
        Optional<Client> previous=Optional.ofNullable(clients.putIfAbsent(entity.getId(), entity));
        previous.ifPresentOrElse(client -> {}, () -> {
            String addClients = "insert into clients (id, name) values(?,?)";
            try {
                PreparedStatement addClientsStatement = connection.prepareStatement(addClients);
                addClientsStatement.setInt(1, entity.getId());
                addClientsStatement.setString(2, entity.getName());
                addClientsStatement.executeUpdate();
            } catch (SQLException e) {
                throw new FileException("There was some problem with the database!");
            }
        });
        return previous;
    }

    @Override
    public Optional<Client> delete(Integer id) {
        Optional<Client> previous=Optional.ofNullable(clients.remove(id));
        previous.ifPresent(client -> {
            try {
                PreparedStatement deleteClientStatement=connection.prepareStatement("delete from clients where id=?");
                deleteClientStatement.setInt(1,id);
                deleteClientStatement.executeUpdate();
            } catch (SQLException e) {
                throw new FileException("There was some problem with the database!");
            }
        });
        return previous;
    }

    @Override
    public Optional<Client> update(Client entity) throws FileException {
        Optional<Client> savedValue=Optional.ofNullable(clients.computeIfPresent(entity.getId(), (k, v) -> entity));
        savedValue.ifPresent( client -> {
            try {
                PreparedStatement clientUpdateStatement=connection.prepareStatement("update clients set name=? where id=?");
                clientUpdateStatement.setString(1,entity.getName());
                clientUpdateStatement.setInt(2,entity.getId());
                clientUpdateStatement.executeUpdate();
            } catch (SQLException e) {
                throw new FileException("There was some problem with the database!");
            }
        });
        return savedValue;
    }
}
