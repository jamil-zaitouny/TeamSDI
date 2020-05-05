package bookshop.web.controller;

import bookshop.core.service.BookService;
import bookshop.core.service.IBookService;
import bookshop.web.converter.BaseConverter;
import bookshop.web.converter.BookConverter;
import bookshop.web.dto.Collection.BooksDto;
import bookshop.web.dto.Model.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    IBookService bookService;

    @Autowired
    BookConverter converter;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    BooksDto saveBook(@RequestBody BookDto bookDto){
        return new BooksDto(

        )
    }
}
