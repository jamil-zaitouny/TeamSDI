package ControllersTests;

import springjpa.Controller.BookController;
import springjpa.Model.Book;
import springjpa.Model.Exceptions.ValidatorException;
import springjpa.Repository.DBRepository.BookDBRepository;
import springjpa.Repository.RepositoryInMemory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

public class BookControllerTest {

    private BookController bookController;

    @Before
    public void setUp() throws IOException {
        bookController=new BookController(new BookDBRepository());
        bookController.addBook("9781234567897","a","a", "a");
        bookController.addBook("9781234567898","b","b", "b");
        bookController.addBook("9781234567899","c","c", "a");
        bookController.addBook("9781234567810","d","d", "b");
        bookController.addBook("9781234567811","e","e", "c");
    }

    @After
    public void tearDown() {
        bookController=null;
    }

    @Test
    public void addBookPositiveTest() throws IOException {
        bookController.addBook("9781234567812","x", "y", "a");
    }

    @Test(expected = ValidatorException.class)
    public void addBookNegativeTest() throws IOException {
        bookController.addBook("", "", "", "");
    }

    @Test
    public void deleteBookPositiveTest()
    {
        this.bookController.deleteBook("9781234567897");
        Assert.assertEquals(4, bookController.getAllBooks().size());
    }

    @Test(expected = NullPointerException.class)
    public void deleteBookNegativeTest()
    {
        this.bookController.deleteBook(null);
    }


    @Test
    public void updateBookPositiveTest() throws IOException {
        String ibsn = "9781234569999";
        String author = "b";
        String title = "b";
        String newTitle = "c";
        this.bookController.addBook(ibsn,title,author, "a");
        this.bookController.updateBook(ibsn, newTitle,author, "a");
        Book book = this.bookController.searchByIbsn(ibsn);
        Assert.assertEquals(book.getTitle(), newTitle);
    }

    @Test
    public void getAllBooksTest()
    {
        Set<Book> books = this.bookController.getAllBooks();
        Assert.assertEquals(5, books.size());
    }

}
