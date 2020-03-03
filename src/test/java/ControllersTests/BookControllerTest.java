package ControllersTests;

import Controller.BookController;
import Model.Book;
import Model.Exceptions.ValidatorException;
import Model.Validators.BookValidator;
import Repository.RepositoryInMemory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

public class BookControllerTest {

    private BookController bookController;

    @Before
    public void setUp() {
        bookController=new BookController(new RepositoryInMemory<>(new BookValidator()));
        bookController.addBook(new Book("9781234567897","a","a"));
        bookController.addBook(new Book("9781234567898","b","b"));
        bookController.addBook(new Book("9781234567899","c","c"));
        bookController.addBook(new Book("9781234567810","d","d"));
        bookController.addBook(new Book("9781234567811","e","e"));
    }

    @After
    public void tearDown() {
        bookController=null;
    }

    @Test
    public void addBookPositiveTest()
    {
        bookController.addBook(new Book("9781234567812","x", "y"));
    }

    @Test(expected = ValidatorException.class)
    public void addBookNegativeTest()
    {
        bookController.addBook(new Book("", "", ""));
    }

    @Test
    public void getAllBooksTest()
    {
        Set<Book> books = this.bookController.getAllBooks();
        Assert.assertEquals(5, books.size());
    }

}
