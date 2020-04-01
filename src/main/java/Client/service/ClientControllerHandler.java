package Client.service;

import Client.TCP.TCPClient;
import Common.Communication.Message;
import Common.HandlerServices.ClientControllerService;
import Model.Book;
import Model.Client;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.CompletableFuture;

public class ClientControllerHandler implements ClientControllerService {
    private ExecutorService executorService;
    private TCPClient tcpClient;

    public ClientControllerHandler(ExecutorService executorService, TCPClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public CompletableFuture<Set<Client>> print_clients() {
        return CompletableFuture.supplyAsync(() ->  {
            Message request = new Message(ClientControllerService.PRINT_CLIENTS, "");
            Message response = tcpClient.sendAndReceive(request);

            Set<Client> clients;
            clients = (Set<Client>) response.getBody();
            return  clients;
        });

    }

    @Override
    public CompletableFuture<Void> addClient(int ID, String name) {
        return CompletableFuture.supplyAsync(() ->    {
            Message request = new Message(ClientControllerService.ADD_CLIENT, ID + " " + name);
            tcpClient.sendAndReceive(request);
            return  null;
        });
    }

    @Override
    public CompletableFuture<Void> deleteClient(int ID) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(ClientControllerService.DELETE_CLIENT, String.valueOf(ID));
            tcpClient.sendAndReceive(request);
            return  null;
        });
    }

    @Override
    public CompletableFuture<Void> updateClient(int ID, String name) {
        return CompletableFuture.supplyAsync(() ->   {
            Message request = new Message(ClientControllerService.UPDATE_CLIENT, ID + " " + name);
            tcpClient.sendAndReceive(request);
            return   null;
        });
    }

    @Override
    public CompletableFuture<Client> searchById(int ID){
        return CompletableFuture.supplyAsync(() ->  {
            Message request = new Message(ClientControllerService.SEARCH_BY_ID, String.valueOf(ID));
            Message response = tcpClient.sendAndReceive(request);
            return  (Client)response.getBody();
        });
    }

    @Override
    public CompletableFuture<Set<Client>> filterByName(String name) {
        return CompletableFuture.supplyAsync(() ->   {
            Message request = new Message(ClientControllerService.FILTER_BY_Name, name);
            Message response = tcpClient.sendAndReceive(request);

            Set<Client> clients;
            clients = (Set<Client>) response.getBody();
            return  clients;
        });
    }

    @Override
    public CompletableFuture<Iterable<Client>> sortClientsByName() {
        return null;
    }
}
