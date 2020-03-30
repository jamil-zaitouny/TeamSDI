package Client.service;

import Client.TCP.TCPClient;
import Common.Communication.Message;
import Common.HandlerServices.BookControllerService;
import Common.HandlerServices.ClientControllerService;
import Model.Book;

import java.util.HashSet;
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
        return executorService.submit(() -> {
            Message request = new Message(BookControllerService.PRINT_BOOKS, "");
            Message response = tcpClient.sendAndReceive(request);

            Set<Book> books = new HashSet<>();
            String[] stringBooks = response.getBody().split("\n");
            for(String stringClient: stringBooks){
                String[] attributes = stringClient.split(" ");
                Book book = new Book(attributes[0], attributes[1], attributes[2], attributes[3]);
                books.add(book);
            }
            return books;
        });
    }

    @Override
    public Future<Void> addBook(String ISBN, String newTitle, String newAuthor, String genre) {
        return executorService.submit(() -> {
            Message request = new Message(BookControllerService.ADD_BOOK, ISBN + " " + newTitle + " " + newAuthor + " " + genre);
            tcpClient.sendAndReceive(request);
            return null;
        });
    }

    @Override
    public Future<Void> deleteBook(String ISBN) {
        return executorService.submit(() -> {
            Message request = new Message(BookControllerService.DELETE_BOOK, String.valueOf(ISBN));
            tcpClient.sendAndReceive(request);
            return null;
        });
    }

    @Override
    public Future<Void> updateBook(String ISBN, String newTitle, String newAuthor, String genre) {
        return executorService.submit(() -> {
            Message request = new Message(BookControllerService.UPDATE_BOOK, ISBN + " " + newTitle + " " + newAuthor + " " + genre);
            tcpClient.sendAndReceive(request);
            return null;
        });
    }

    @Override
    public Future<Book> searchByIsbn(String ISBN) {
        return executorService.submit(() -> {
            Message request = new Message(BookControllerService.SEARCH_BY_ISBN, "");
            Message response = tcpClient.sendAndReceive(request);

            String[] stringBooks = response.getBody().split("\n");
            return new Book(stringBooks[0], stringBooks[1], stringBooks[2], stringBooks[3]);
        });
    }

    @Override
    public Future<Set<Book>> filterByGenre() {
        return executorService.submit(() -> {
            Message request = new Message(BookControllerService.FILTER_BY_GENRE, "");
            Message response = tcpClient.sendAndReceive(request);

            Set<Book> books = new HashSet<>();
            String[] stringBooks = response.getBody().split("\n");
            for(String stringClient: stringBooks){
                String[] attributes = stringClient.split(" ");
                Book book = new Book(attributes[0], attributes[1], attributes[2], attributes[3]);
                books.add(book);
            }
            return books;
        });
    }
}
