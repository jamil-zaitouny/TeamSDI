package InMemoryRepositoryTests;

import Model.Book;
import Model.Exceptions.ValidatorException;
import Model.Validators.BookValidator;
import Repository.BookRepositoryInMemmory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

public class BooksInMemoryRepositoryTests
{

    private BookRepositoryInMemmory books;

    @Before
    public void setUp() throws Exception {
        books=new BookRepositoryInMemmory(new BookValidator());
        books.add(new Book("1","a","a"));
        books.add(new Book("2","b","b"));
        books.add(new Book("3","c","c"));
        books.add(new Book("4","d","d"));
        books.add(new Book("5","e","e"));
    }

    @After
    public void tearDown() throws Exception {
        books=null;
    }

    @Test
    public void testFindOne() throws Exception {

        assertEquals("Failed",new Book("2","b","b"),books.findOne("2").get());
    }

    @Test
    public void testAdd() throws Exception {
        assertNotNull(books.add(new Book("6","e","e")));
    }

    @Test
    public void testDelete() throws Exception {
        assertNotNull(books.delete("4"));
    }

    @Test
    public void testUpdate() throws Exception {
        assertNotNull(books.update(new Book("1","b","c")));
    }

}
