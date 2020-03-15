package ControllersTests;

import Controller.ClientController;
import Model.Client;
import Model.Exceptions.ValidatorException;
import Repository.RepositoryInMemory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

public class ClientControllerTest {
    private ClientController clientController;

    @Before
    public void setUp() throws IOException {
        clientController=new ClientController(new RepositoryInMemory<>());
        clientController.addClient(1,"a");
        clientController.addClient(2,"a");
        clientController.addClient(3,"a");
        clientController.addClient(4,"a");
        clientController.addClient(5,"a");
    }

    @After
    public void tearDown() {
        clientController=null;
    }

    @Test
    public void addClientPositiveTest() throws IOException {
        this.clientController.addClient(10, "abc");
    }

    @Test(expected = ValidatorException.class)
    public void addClientNegativeTest() throws IOException {
        this.clientController.addClient(7, "");
        this.clientController.addClient(-1, "abc");
    }

    @Test
    public void deleteClientPositiveTest()
    {
        this.clientController.deleteClient(2);
        Assert.assertEquals(4, this.clientController.getAllClients().size());
    }

    @Test(expected = NullPointerException.class)
    public void deleteClientNegativeTest()
    {
        this.clientController.deleteClient(null);
    }

    @Test
    public void updateBookPositiveTest() throws IOException {
        int id = 10;
        String name = "b";
        String newName = "c";
        this.clientController.addClient(id, name);
        this.clientController.updateClient(id ,newName);
        Client client = this.clientController.searchById(id);
        Assert.assertEquals(client.getName(), newName);
    }


    @Test
    public void getAllTest()   {
        Set<Client> clients = this.clientController.getAllClients();
        Assert.assertEquals(5, clients.size());
    }
}
