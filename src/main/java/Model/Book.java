package Model;

import Repository.XMLRepositories.XMLUtilities;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.sql.rowset.spi.XmlReader;
import java.io.Serializable;
import java.util.Optional;

public class Book extends BaseEntity<String> implements Serializable {
    private String title;
    private String authorName;
    private String genre;

    public Book(){
        super("2");
    }

    public Book(String ISBN, String title, String authorName, String genre) {
        super(ISBN);
        this.title = title;
        this.authorName = authorName;
        this.genre = genre;
    }
    public Book(Element bookElement){
            super(XMLUtilities.getTextFromTagName(bookElement, "isbn"));
            this.authorName = XMLUtilities.getTextFromTagName(bookElement, "author");
            this.title = XMLUtilities.getTextFromTagName(bookElement, "title");
            this.genre = XMLUtilities.getTextFromTagName(bookElement, "genre");

    }

    @Override
    public boolean equals(Object obj) {
        Optional<Object> optionalO = Optional.ofNullable(obj).filter(v -> v instanceof Book);
        return optionalO.filter(v -> ((Book) v).getId().equals(this.getId())).isPresent();
    }

    @Override
    public String toString() {
        return "<ISBN: " + this.getId() + "\n"
                + "Title: " + this.title + "\n"
                + "Author: " + this.authorName + "\n"
                + "Genre: " + this.getGenre() + ">";
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getGenre() {
        return genre;
    }

}
