package Server.TCP;

import Common.Communication.Message;
import Common.HandlerServices.BookControllerService;
import Common.HandlerServices.ClientControllerService;
import Common.HandlerServices.PurchaseControllerService;
import Model.Book;
import Model.Client;
import Server.service.BookControllerHandler;
import Server.service.ClientControllerHandler;
import Server.service.PurchaseControllerHandler;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

//Fills the
public class FillMethodHandler {

    public static TCPServer fillWithAllMethods(TCPServer server){
        return server;
    }

    public static TCPServer fillBookMethods(TCPServer server, BookControllerHandler bookControllerHandler){
        TCPServer currentServer = server;
        currentServer.addHandler(BookControllerService.ADD_BOOK, (request) -> {
            String[] params = request.getBody().split(" ");
            Future<Void> future = bookControllerHandler.addBook(params[0], params[1], params[2], params[3]);
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
        currentServer.addHandler(BookControllerService.DELETE_BOOK, (request) -> {
            Future<Void> future = bookControllerHandler.deleteBook(request.getBody());
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
        currentServer.addHandler(BookControllerService.UPDATE_BOOK, (request) -> {
            String[] params = request.getBody().split(" ");
            Future<Void> future = bookControllerHandler.updateBook(params[0], params[1], params[2], params[3]);
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
        currentServer.addHandler(BookControllerService.FILTER_BY_GENRE, (request) -> {
            String[] params = request.getBody().split(" ");
            Future<Set<Book>> future = bookControllerHandler.filterByGenre(params[0]);
            future.isDone();
            try {
                Set<Book> filteredBooks = future.get();
                String response = "";
                for(Book book: filteredBooks){
                    response += book.getId() + " " + book.getTitle() + " " + book.getAuthorName() + " " + book.getGenre()+"\n";
                }
                return new Message("ok", response); //fixme: hardcoded str
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message("error", ""); //fixme: hardcoded str
            }
        });
        currentServer.addHandler(BookControllerService.PRINT_BOOKS, (request) -> {
            Future<Set<Book>> future = bookControllerHandler.print_books();
            future.isDone();
            try {
                Set<Book> books = future.get();
                String response = "";
                for(Book book: books){
                    response += book.getId() + " " + book.getTitle() + " " + book.getAuthorName() + " " + book.getGenre()+"\n";
                }
                return new Message("ok", response); //fixme: hardcoded str
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message("error", ""); //fixme: hardcoded str
            }
        });
        currentServer.addHandler(BookControllerService.SEARCH_BY_ISBN, (request) -> {
            Future<Book> future = bookControllerHandler.searchByIsbn(request.getBody());
            future.isDone();
            try {
                Book book = future.get();
                return new Message("ok", book.getId() + " " + book.getTitle() + " " + book.getAuthorName() + " " + book.getGenre()); //fixme: hardcoded str
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message("error", ""); //fixme: hardcoded str
            }
        });
        return currentServer;
    }

    public static TCPServer fillClientMethods(TCPServer server, ClientControllerHandler clientControllerHandler){
        TCPServer currentServer = server;
        currentServer.addHandler(ClientControllerService.ADD_CLIENT, (request) -> {
            String[] params = request.getBody().split(" ");
            Future<Void> future = clientControllerHandler.addClient(Integer.parseInt(params[0]), params[1]);
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
        currentServer.addHandler(ClientControllerService.DELETE_CLIENT, (request) -> {
            Future<Void> future = clientControllerHandler.deleteClient(Integer.parseInt(request.getBody()));
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
        currentServer.addHandler(ClientControllerService.UPDATE_CLIENT, (request) -> {
            String[] params = request.getBody().split(" ");
            Future<Void> future = clientControllerHandler.updateClient(Integer.parseInt(params[0]), params[1]);
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
        currentServer.addHandler(ClientControllerService.FILTER_BY_Name, (request) -> {
            Future<Set<Client>> future = clientControllerHandler.filterByName(request.getBody());
            future.isDone();
            try {
                Set<Client> filteredClients = future.get();
                String response = "";
                for(Client client: filteredClients){
                    response += String.valueOf(client.getId()) + " " + client.getName() + "\n";
                }
                return new Message("ok", response); //fixme: hardcoded str
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message("error", ""); //fixme: hardcoded str
            }
        });
        currentServer.addHandler(ClientControllerService.PRINT_CLIENTS, (request) -> {
            Future<Set<Client>> future = clientControllerHandler.print_clients();
            future.isDone();
            try {
                Set<Client> clients = future.get();
                String response = "";
                for(Client client: clients){
                    response += String.valueOf(client.getId()) + " " + client.getName() + "\n";
                }
                return new Message("ok", response); //fixme: hardcoded str
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message("error", ""); //fixme: hardcoded str
            }
        });
        currentServer.addHandler(ClientControllerService.SEARCH_BY_ID, (request) -> {
            Future<Client> future = clientControllerHandler.searchById(Integer.parseInt(request.getBody()));
            future.isDone();
            try {
                Client client = future.get();
                return new Message("ok", String.valueOf(client.getId()) + " " + client.getName()); //fixme: hardcoded str
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message("error", ""); //fixme: hardcoded str
            }
        });
        return currentServer;
    }

    public static TCPServer fillPurchaseMethods(TCPServer server, PurchaseControllerHandler purchaseControllerHandler){
        TCPServer currentServer = server;
        currentServer.addHandler(PurchaseControllerService.ADD_PURCHASE, (request) -> {
            String[] params = request.getBody().split(" ");
            Future<Void> future = purchaseControllerHandler.addPurchase(Integer.parseInt(params[0]),params[1],Integer.parseInt(params[2]), params[3]);
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
        currentServer.addHandler(PurchaseControllerService.DELETE_PURCHASE, (request) -> {
            Future<Void> future = purchaseControllerHandler.deletePurchase(Integer.parseInt(request.getBody()));
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
//        currentServer.addHandler(PurchaseControllerService.UPDATE_PURCHASE, (request) -> {
//            String[] params = request.getBody().split(" ");
//            Future<Void> future = purchaseControllerHandler.updatePurchase(Integer.parseInt(params[0]), params[3]);
//            future.isDone();
//            return new Message("ok", ""); //fixme: hardcoded str
//        });
//        currentServer.addHandler(PurchaseControllerService.BOOKS_WITH_HIGHEST_PURCHASE_COUNT, (request) -> {
//            Future<List<String>> future = purchaseControllerHandler.booksWithHighestPurchaseCount();
//            future.isDone();
//            try {
//                List<String> filteredBooks = future.get();
//                String response = "";
//                for(String string: filteredBooks){
//                    response += string + "\n";
//                }
//                return new Message("ok", response); //fixme: hardcoded str
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//                return new Message("error", ""); //fixme: hardcoded str
//            }
//        });
//        currentServer.addHandler(PurchaseControllerService.BOOKS_WITH_HIGHEST_PURCHASE_COUNT_PER_GENRE, (request) -> {
//            Future<List<String>> future = purchaseControllerHandler.booksWithHighestPurchaseCountPerGenre();
//            future.isDone();
//            try {
//                List<String> books = future.get();
//                String response = "";
//                for(String string: books){
//                    response += string + "\n";
//                }
//                return new Message("ok", response); //fixme: hardcoded str
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//                return new Message("error", ""); //fixme: hardcoded str
//            }
//        });
//        currentServer.addHandler(PurchaseControllerService.CLIENTS_WITH_MOST_PURCHASES, (request) -> {
//            Future<List<String>> future = purchaseControllerHandler.clientsWithMostPurchases();
//            future.isDone();
//            try {
//                List<String> books = future.get();
//                String response = "";
//                for(String string: books){
//                    response += string + "\n";
//                }
//                return new Message("ok", response); //fixme: hardcoded str
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//                return new Message("error", ""); //fixme: hardcoded str
//            }
//        });
//
            return currentServer;
    }
}
