package XMLRepositoryTest;

import springjpa.Model.Client;
import springjpa.Repository.RepositoryInterface;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import springjpa.Repository.XMLRepositories.ClientXMLRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ClientFileRepositoryTest {
    private RepositoryInterface clients;

    @Before
    public void setUp() throws Exception {
        clients=new ClientXMLRepository(".\\src\\main\\java\\Files\\","ClientTest.xml");
    }

    @After
    public void tearDown() throws Exception {
        clients=null;
    }

    @Test
    public void testFindOne() throws Exception {

        assertEquals("Failed",new Client(6,"a"),clients.findOne(6).get());
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
