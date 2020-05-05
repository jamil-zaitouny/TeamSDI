package bookshop.web.dto.Model;

import bookshop.web.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class BookDto extends BaseDto<String> {
    private String title;
    private String authorName;
    private String genre;
    public BookDto(String ISBN, String title, String authorName, String genre){
        super(ISBN);
        this.title = title;
        this.authorName = authorName;
        this.genre = genre;
    }
}
