package Server.service;
import Controller.BookController;
import Common.HandlerServices.BookControllerService;
import Model.Book;

import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;

public class BookControllerHandler implements BookControllerService {
    ExecutorService executorService;
    BookController bookController;

    public BookControllerHandler(ExecutorService executorService, BookController bookController){
        this.executorService = executorService;
        this.bookController = bookController;
    }
    @Override
    public Future<Set<Model.Book>> print_books() {
        return executorService.submit(()-> bookController.getAllBooks());
    }

    @Override
    public Future<Void> deleteBook(String ISBN) {
        return executorService.submit(() -> {
            bookController.deleteBook(ISBN);
            return null;
        });
    }

    @Override
    public Future<Void> updateBook(String ISBN, String newTitle, String newAuthor, String genre) {
        return executorService.submit(() -> {
            bookController.updateBook(ISBN, newTitle, newAuthor, genre);
            return null;
        });
    }

    @Override
    public Future<Model.Book> searchByIsbn(String ISBN) {
        return executorService.submit(() ->{
            Book book = bookController.searchByIbsn(ISBN);
            return book;
        } );
    }

    @Override
    public Future<Set<Book>> filterByGenre(String genre) {
        return executorService.submit(()-> bookController.filterByGenre(genre));
    }

    @Override
    public Future<Iterable<Book>> sortBooksByTitleAuthor() {
        return null;
    }

    @Override
    public Future<Void> addBook(String ISBN, String newTitle, String newAuthor, String genre) {
        return executorService.submit(() -> {
            bookController.addBook(ISBN, newTitle, newAuthor, genre);
            return null;
        });
    }
}
