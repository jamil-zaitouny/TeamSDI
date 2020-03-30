package Client.service;

import Client.TCP.TCPClient;
import Common.Communication.Message;
import Common.HandlerServices.ClientControllerService;
import Model.Client;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ClientControllerHandler implements ClientControllerService {
    private ExecutorService executorService;
    private TCPClient tcpClient;

    public ClientControllerHandler(ExecutorService executorService, TCPClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public Future<Set<Client>> print_clients() {
        return executorService.submit(() -> {
            Message request = new Message(ClientControllerService.PRINT_CLIENTS, "");
            Message response = tcpClient.sendAndReceive(request);

            Set<Client> clients = new HashSet<>();
            String[] stringClients = response.getBody().split("\n");
            for(String stringClient: stringClients){
                String[] attributes = stringClient.split(" ");
                Client client = new Client(Integer.parseInt(attributes[0]), attributes[1]);
                clients.add(client);
            }
            return clients;
        });
    }

    @Override
    public Future<Void> addClient(int ID, String name) {
        return executorService.submit(() -> {
            Message request = new Message(ClientControllerService.ADD_CLIENT, ID + " " + name);
            tcpClient.sendAndReceive(request);
            return null;
        });
    }

    @Override
    public Future<Void> deleteClient(int ID) {
        return executorService.submit(() -> {
            Message request = new Message(ClientControllerService.DELETE_CLIENT, String.valueOf(ID));
            tcpClient.sendAndReceive(request);
            return null;
        });
    }

    @Override
    public Future<Void> updateClient(int ID, String name) {
        return executorService.submit(() -> {
            Message request = new Message(ClientControllerService.UPDATE_CLIENT, ID + " " + name);
            tcpClient.sendAndReceive(request);
            return null;
        });    }

    @Override
    public Future<Client> searchById(int ID){
        return executorService.submit(() -> {
            Message request = new Message(ClientControllerService.SEARCH_BY_ID, String.valueOf(ID));
            Message response = tcpClient.sendAndReceive(request);

            String[] stringClient = response.getBody().split(" ");
            return new Client(Integer.parseInt(stringClient[0]), stringClient[1]);
        });
    }

    @Override
    public Future<Set<Client>> filterByName(String name) {
        return executorService.submit(() -> {
            Message request = new Message(ClientControllerService.FILTER_BY_Name, name);
            Message response = tcpClient.sendAndReceive(request);

            Set<Client> clients = new HashSet<>();
            String[] stringClients = response.getBody().split("\n");
            for(String stringClient: stringClients){
                String[] attributes = stringClient.split(" ");
                Client client = new Client(Integer.parseInt(attributes[0]), attributes[1]);
                clients.add(client);
            }
            return clients;
        });
    }

    @Override
    public Future<Iterable<Client>> sortClientsByName() {
        return null;
    }
}
