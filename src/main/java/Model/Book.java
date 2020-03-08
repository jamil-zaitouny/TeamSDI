package Model;

import java.util.Optional;

public class Book extends BaseEntity<String> implements FileOperations {
    private String title;
    private String authorName;

    public Book(String ISBN, String title, String authorName) {
        super(ISBN);
        this.title = title;
        this.authorName = authorName;
    }

    @Override
    public boolean equals(Object obj) {
        Optional<Object> optionalO = Optional.ofNullable(obj).filter(v -> v instanceof Book);
        return optionalO.filter(v -> ((Book) v).getId().equals(this.getId())).isPresent();
    }

    @Override
    public String toString() {
        return "ISBN: " + this.getId() + "\n"
                +"Title: " + this.title + "\n"
                +"Author: " + this.authorName + "\n";
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName(){return authorName;}
    public void setAuthorName(String authorName){this.authorName = authorName;}


    @Override
    public String[] toCSV() {
        return (getId() + "," + getTitle() + "," + getAuthorName()).split(",");
    }

    @Override
    public String toXML() {
        return null;
    }
}
