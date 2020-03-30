package Server.service;
import Controller.BookController;
import Common.HandlerServices.BookControllerService;

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
        return executorService.submit(()->bookController.getAllBooks());
    }

    @Override
    public Future<Void> delete_book(String ISBN) {
        return executorService.submit(() -> {
            bookController.deleteBook(ISBN);
            return null;
        });
    }

    @Override
    public Future<Void> update_book(String ISBN, String newTitle, String newAuthor, String genre) {
        return executorService.submit(() -> {
            bookController.updateBook(ISBN, newTitle, newAuthor, genre);
            return null;
        });
    }

    @Override
    public Future<Model.Book> search_by_isbn(String ISBN) {
        return executorService.submit(() -> bookController.searchByIbsn(ISBN));
    }

    @Override
    public Future<Set<Model.Book>> filter_by_genre() {
        return null;
    }

    @Override
    public Future<Void> add_book(String ISBN, String newTitle, String newAuthor, String genre) {
        return executorService.submit(() -> {
            bookController.addBook(ISBN, newTitle, newAuthor, genre);
            return null;
        });
    }
}
