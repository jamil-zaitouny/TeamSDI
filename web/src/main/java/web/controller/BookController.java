package web.controller;

import core.service.BookService;
import core.service.BookServiceInterface;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.converter.BookConverter;
import web.dto.BookDto;
import web.dto.BooksDto;

import java.rmi.ServerError;

@RestController
public class BookController
{
    final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookServiceInterface bookService;

    @Autowired
    private BookConverter bookConverter;

    @RequestMapping(value = "/books",method= RequestMethod.GET)
    BooksDto getBooks()
    {
        logger.info("printing books");
        return new BooksDto(bookConverter.convertModelsToDtos(bookService.getAllBooks()));
    }

    @RequestMapping(value = "/books/filter",method = RequestMethod.GET)
    BooksDto filterByGenre(@RequestParam(value ="genre") String genre)
    {
        logger.trace("filtering books");
        return new BooksDto(bookConverter.convertModelsToDtos(bookService.filterByGenre(genre)));
    }

    @RequestMapping(value = "/books",method= RequestMethod.POST)
    ResponseEntity<?> saveBook(@RequestBody BookDto bookDto)
    {
        logger.trace("adding book");
        try {
            bookService.addBook(bookConverter.convertDtoToModel(bookDto));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (ServiceException e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> updateBook(@PathVariable String id, @RequestBody BookDto bookDto)
    {
        logger.trace("updating book");
        try {
            bookService.updateBook(bookConverter.convertDtoToModel(bookDto));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (ServiceException e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/books/{id}",method = RequestMethod.DELETE)
    ResponseEntity<?> deleteBook(@PathVariable String id)
    {
        logger.trace("deleting book");
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
