package client.ui;

import core.model.Client;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import web.dto.ClientDto;
import web.dto.ClientsDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class ClientConsole extends DefaultConsole {
    public static final String URL = "http://localhost:8080/api";
    private RestTemplate restTemplate;

    private static final int PrintClientsOption = 1;
    private static final int AddClientOption = 2;
    private static final int DeleteClientOption = 3;
    private static final int UpdateClientOption = 4;
    private static final int FilterByName = 5;

    ClientConsole(RestTemplate restTemplate) {
        this.restTemplate=restTemplate;
    }

    @Override
    protected int dealChoice(int choice) throws IOException {
        switch (choice) {
            case PrintClientsOption:
                printClients();
                break;
            case AddClientOption:
                addClient();
                break;
            case DeleteClientOption:
                deleteClient();
                break;
            case UpdateClientOption:
                updateClient();
                break;
            case FilterByName:
                filterByName();
                break;
            case ExitOption:
                return -1;
            default:
                System.out.println("Wrong option! Try again!");
                break;
        }
        return 0;
    }

    private void filterByName() throws IOException{
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Name: ");
        String name = bufferRead.readLine();

        ClientsDto allClients=restTemplate.getForObject(URL+"/clients/filter?name={name}",ClientsDto.class,name);
        List<ClientDto> clients=allClients.getClients();
        clients.forEach(System.out::println);
    }

    private void updateClient() throws IOException{
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Id: ");
        int id = Integer.parseInt(bufferRead.readLine());
        System.out.println("New name: ");
        String name = bufferRead.readLine();

        ClientDto clientDto= new ClientDto(name);
        clientDto.setId(id);

        try {
            restTemplate.put(URL+"/clients/{id}" , clientDto, id);
        }
        catch (RestClientException e)
        {
            System.out.println("Can't update");
        }
    }

    private void deleteClient() throws IOException {
        System.out.println("Id: ");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(bufferedReader.readLine());

        restTemplate.delete(URL+"/clients/{id}",id);
        //this.purchaseControllercontroller.deleteAllPurchasesForClient(id);
    }

    private void addClient() throws IOException {
        System.out.println("Read Client {Id, Client}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(bufferRead.readLine());
        String name = bufferRead.readLine();
        ClientDto clientDto= new ClientDto(name);
        clientDto.setId(id);

        try {
            restTemplate.postForObject(URL+"/clients" , clientDto, ClientDto.class);
        }
        catch (RestClientException e)
        {
            System.out.println("Can't add");
        }
    }

    private void printClients() {
        ClientsDto allClients=restTemplate.getForObject(URL+"/clients",ClientsDto.class);
        List<ClientDto> clients=allClients.getClients();
        clients.forEach(System.out::println);
    }

    @Override
    protected void displayMenu() {
        System.out.println("Options: ");
        System.out.println("\t1.Print clients");
        System.out.println("\t2.Add client");
        System.out.println("\t3.Delete client");
        System.out.println("\t4.Update client");
        System.out.println("\t5.Filter by name");
        System.out.println("\t0.Go back");
    }
}
