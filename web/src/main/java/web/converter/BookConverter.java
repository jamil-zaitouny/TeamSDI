package web.converter;

import core.model.Book;
import org.springframework.stereotype.Component;
import web.dto.BookDto;

@Component
public class BookConverter extends BaseConverter<String, Book, BookDto> {
    @Override
    public Book convertDtoToModel(BookDto dto) {
       Book book=Book.builder().authorName(dto.getAuthorName()).genre(dto.getGenre()).title(dto.getTitle()).build();
       book.setId(dto.getId());
       return book;
    }

    @Override
    public BookDto convertModelToDto(Book book) {
        BookDto dto=BookDto.builder().authorName(book.getAuthorName()).genre(book.getGenre()).title(book.getTitle()).build();
        dto.setId(book.getId());
        return dto;
    }
}
