package Client.service;

import Client.TCP.TCPClient;
import Common.Communication.Message;
import Common.HandlerServices.BookControllerService;
import Common.HandlerServices.PurchaseControllerService;
import Model.Book;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class BookControllerHandler implements BookControllerService {
    private ExecutorService executorService;
    private TCPClient tcpClient;

    public BookControllerHandler(ExecutorService executorService, TCPClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public Future<Set<Book>> print_books() {
        return null;
    }

    @Override
    public Future<Void> addBook(String ISBN, String newTitle, String newAuthor, String genre) {
        return executorService.submit(() -> {
            Message request = new Message(BookControllerService.ADD_BOOK, ISBN + " " + newTitle + " " + newAuthor + " " + genre);
            Message response = tcpClient.sendAndReceive(request);
            return null;
        });
    }

    @Override
    public Future<Void> deleteBook(String ISBN) {
        return executorService.submit(() -> {
            Message request = new Message(BookControllerService.DELETE_BOOK, String.valueOf(ISBN));
            Message response = tcpClient.sendAndReceive(request);
            return null;
        });
    }

    @Override
    public Future<Void> updateBook(String ISBN, String newTitle, String newAuthor, String genre) {
        return executorService.submit(() -> {
            Message request = new Message(BookControllerService.UPDATE_BOOK, ISBN + " " + newTitle + " " + newAuthor + " " + genre);
            Message response = tcpClient.sendAndReceive(request);
            return null;
        });
    }

    @Override
    public Future<Book> searchByIsbn(String ISBN) {
        return null;
    }

    @Override
    public Future<Set<Book>> filterByGenre() {
        return null;
    }
}
