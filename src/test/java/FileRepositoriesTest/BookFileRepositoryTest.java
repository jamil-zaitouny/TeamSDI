package FileRepositoriesTest;

import Model.Book;
import Repository.FileRepositories.BookFileRepository;
import Repository.RepositoryInterface;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BookFileRepositoryTest {
    private RepositoryInterface books;

    @Before
    public void setUp() throws Exception {
        books=new BookFileRepository("BookTest.csv");
    }

    @After
    public void tearDown() throws Exception {
        books=null;
    }

    @Test
    public void testFindOne() throws Exception {
        assertEquals(new Book("9781234567810","d","d"),books.findOne("9781234567810").get());
    }

    @Test
    public void testAdd() throws Exception {
        assertNotNull(books.add(new Book("9781234567821","e","e")));
    }

    @Test
    public void testDelete() throws Exception {
        assertNotNull(books.delete("9781234567811"));
    }

    @Test
    public void testUpdate() throws Exception {
        assertNotNull(books.update(new Book("9781234567811","b","c")));
    }
    @Test
    public void testSize() throws Exception{
        AtomicInteger accumulator = new AtomicInteger();
        books.findAll().forEach(v-> accumulator.addAndGet(1));
        assertEquals(accumulator.get(), 9);
    }
}
