package InMemoryRepositoryTests;

import springjpa.Model.Client;
import springjpa.Repository.RepositoryInMemory;
import springjpa.Repository.RepositoryInterface;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ClientsInMemoryRepositoryTests {
    private RepositoryInterface clients;

    @Before
    public void setUp() throws Exception {
        clients=new RepositoryInMemory();
        clients.add(new Client(1,"a"));
        clients.add(new Client(2,"a"));
        clients.add(new Client(3,"a"));
        clients.add(new Client(4,"a"));
        clients.add(new Client(5,"a"));
    }

    @After
    public void tearDown() throws Exception {
        clients=null;
    }

    @Test
    public void testFindOne() throws Exception {

        assertEquals("Failed",new Client(2,"a"),clients.findOne(2).get());
    }

    @Test
    public void testAdd() throws Exception {
        assertNotNull(clients.add(new Client(6,"e")));
    }

    @Test
    public void testDelete() throws Exception {
        assertNotNull(clients.delete(4));
    }

    @Test
    public void testUpdate() throws Exception {
        assertNotNull(clients.update(new Client(1,"z")));
    }
}
