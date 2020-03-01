package ControllersTests;

import Controller.ClientController;
import Model.Client;
import Model.Exceptions.ValidatorException;
import Model.Validators.ClientValidator;
import Repository.ClientRepositoryInMemory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

public class ClientControllerTest {
    private ClientController clientController;

    @Before
    public void setUp()  {
        clientController=new ClientController(new ClientRepositoryInMemory<>(new ClientValidator()));
        clientController.addClient(new Client(1,"a"));
        clientController.addClient(new Client(2,"a"));
        clientController.addClient(new Client(3,"a"));
        clientController.addClient(new Client(4,"a"));
        clientController.addClient(new Client(5,"a"));
    }

    @After
    public void tearDown() {
        clientController=null;
    }

    @Test
    public void addClientPositiveTest() {
        this.clientController.addClient(new Client(10, "abc"));
    }

    @Test(expected = ValidatorException.class)
    public void addClientNegativeTest()  {
        this.clientController.addClient(new Client(7, ""));
        this.clientController.addClient(new Client(-1, "abc"));
    }

    @Test
    public void getAllTest()   {
        Set<Client> clients = this.clientController.getAllClients();
        Assert.assertEquals(5, clients.size());
    }
}
