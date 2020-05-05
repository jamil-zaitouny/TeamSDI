package bookshop.web.converter;

import bookshop.core.model.Book;
import bookshop.web.dto.Model.BookDto;
import org.springframework.stereotype.Component;

@Component
public class BookConverter implements Converter<Book, BookDto>{

    @Override
    public Book convertDtoToModel(BookDto dto) {
        return new Book(dto.getId(), dto.getTitle(), dto.getAuthorName(), dto.getGenre());
    }

    @Override
    public BookDto convertModelToDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthorName(), book.getGenre());
    }

}
