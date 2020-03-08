package InMemoryRepositoryTests;

import Model.Book;
import Repository.RepositoryInMemory;
import Repository.RepositoryInterface;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BooksInMemoryRepositoryTests
{

    private RepositoryInterface books;

    @Before
    public void setUp() throws Exception {
        books=new RepositoryInMemory<String, Book>();
        books.add(new Book("9781234567897","a","a"));
        books.add(new Book("9781234567898","b","b"));
        books.add(new Book("9781234567899","c","c"));
        books.add(new Book("9781234567810","d","d"));
        books.add(new Book("9781234567811","e","e"));
    }

    @After
    public void tearDown() throws Exception {
        books=null;
    }

    @Test
    public void testFindOne() throws Exception {
        //TODO
        assertEquals("Failed",new Book("9781234567811","b","b"),books.findOne("9781234567811").get());
    }

    @Test
    public void testAdd() throws Exception {
        //TODO
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

}
