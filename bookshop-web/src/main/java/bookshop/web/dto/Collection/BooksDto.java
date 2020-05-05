package bookshop.web.dto.Collection;


import bookshop.web.dto.BaseDto;
import bookshop.web.dto.Model.BookDto;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BooksDto{
    private Set<BookDto> books;
}
