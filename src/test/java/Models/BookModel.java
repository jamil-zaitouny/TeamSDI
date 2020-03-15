package Models;

import Model.Book;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class BookModel {
    private final static String ISBN = "9781234567897";
    private final static String TITLE = "Red Rising";
    private final static String AUTHOR = "Pierce Brown";
    private final static String GENRE = "Pierce Brown";

    private Book book;

    @Before
    public void setUp(){
        book = new Book(ISBN, TITLE, AUTHOR, GENRE);
    }
    @After
    public void tearDown(){
        book = null;
    }

    @Test
    public void testGetBookID() throws Exception{
        Assert.assertEquals( "The ID should be, ",ISBN, book.getId());
    }
    @Test
    public void testSetBookID() throws Exception{
        book.setId("2");
        Assert.assertEquals( "The ID should be",book.getId(),  "2");
    }

    @Test
    public void testGetBookTitle() throws Exception{
        Assert.assertEquals("The title should be, ",TITLE, book.getTitle());
    }
    @Test
    public void testSetBookTitle() throws Exception {
        book.setTitle("bla");
        Assert.assertEquals("The Title should be",book.getTitle(), "bla");
    }

    @Test
    public void testGetBookAuthor() throws Exception{
        Assert.assertEquals("The AuthorName should be, ",AUTHOR, book.getAuthorName());
    }
    @Test
    public void testSetBookAuthor() throws Exception {
        book.setAuthorName("nslkjfbnlsdkfb");
        Assert.assertEquals( "The authorName should be",book.getAuthorName(), "nslkjfbnlsdkfb");
    }
}
