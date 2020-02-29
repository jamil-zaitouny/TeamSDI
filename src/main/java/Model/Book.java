package Model;

public class Book extends BaseEntity<String> {
    private String title;
    private String authorName;

    public Book(String IBAN, String title, String authorName) {
        super(IBAN);
        this.title = title;
        this.authorName = authorName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Book){
            return ((Book) obj).getId().equals(this.getId());
        }
        return false;
    }

    @Override
    public String toString() {
        return "IBAN: " + this.getId() + "\n"
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


}
