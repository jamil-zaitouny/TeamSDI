package bookshop.core.model;

import lombok.*;

import javax.persistence.Entity;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class Book extends BaseEntity<String> {
    private String title;
    private String authorName;
    private String genre;
    public Book(String ISBN, String title, String authorName, String genre){
        super(ISBN);
        this.title = title;
        this.authorName = authorName;
        this.genre = genre;
    }
}
