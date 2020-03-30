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
            Future<Void> future = bookControllerHandler.add_book(params[0], params[1], params[2], params[3]);
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
        currentServer.addHandler(BookControllerService.DELETE_BOOK, (request) -> {
            Future<Void> future = bookControllerHandler.delete_book(request.getBody());
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
        currentServer.addHandler(BookControllerService.UPDATE_BOOK, (request) -> {
            String[] params = request.getBody().split(" ");
            Future<Void> future = bookControllerHandler.update_book(params[0], params[1], params[2], params[3]);
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
        currentServer.addHandler(BookControllerService.FILTER_BY_GENRE, (request) -> {
            Future<Set<Book>> future = bookControllerHandler.filter_by_genre();
            future.isDone();
            try {
                Set<Book> filteredBooks = future.get();
                String response = "";
                for(Book book: filteredBooks){
                    response += book.toString() + "\n";
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
                    response += book.toString() + "\n";
                }
                return new Message("ok", response); //fixme: hardcoded str
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message("error", ""); //fixme: hardcoded str
            }
        });
        currentServer.addHandler(BookControllerService.SEARCH_BY_ISBN, (request) -> {
            Future<Book> future = bookControllerHandler.search_by_isbn(request.getBody());
            future.isDone();
            try {
                return new Message("ok", future.get().toString()); //fixme: hardcoded str
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
            Future<Void> future = clientControllerHandler.add_client(Integer.parseInt(params[0]), params[1]);
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
        currentServer.addHandler(ClientControllerService.DELETE_CLIENT, (request) -> {
            Future<Void> future = clientControllerHandler.delete_client(Integer.parseInt(request.getBody()));
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
        currentServer.addHandler(ClientControllerService.UPDATE_CLIENT, (request) -> {
            String[] params = request.getBody().split(" ");
            Future<Void> future = clientControllerHandler.update_client(Integer.parseInt(params[0]), params[1]);
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
        currentServer.addHandler(ClientControllerService.FILTER_BY_Name, (request) -> {
            Future<Set<Client>> future = clientControllerHandler.filter_by_name(request.getBody());
            future.isDone();
            try {
                Set<Client> filteredClients = future.get();
                String response = "";
                for(Client client: filteredClients){
                    response += client.toString() + "\n";
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
                    response += client.toString() + "\n";
                }
                return new Message("ok", response); //fixme: hardcoded str
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message("error", ""); //fixme: hardcoded str
            }
        });
        currentServer.addHandler(ClientControllerService.SEARCH_BY_ID, (request) -> {
            Future<Client> future = clientControllerHandler.search_by_id(Integer.parseInt(request.getBody()));
            future.isDone();
            try {
                return new Message("ok", future.get().toString()); //fixme: hardcoded str
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
            Future<Void> future = purchaseControllerHandler.add_purchase(Integer.parseInt(params[0]),params[1],Integer.parseInt(params[2]), params[3]);
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
        currentServer.addHandler(PurchaseControllerService.DELETE_PURCHASE, (request) -> {
            Future<Void> future = purchaseControllerHandler.delete_purchase(Integer.parseInt(request.getBody()));
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
        currentServer.addHandler(PurchaseControllerService.UPDATE_PURCHASE, (request) -> {
            String[] params = request.getBody().split(" ");
            Future<Void> future = purchaseControllerHandler.update_purchase(Integer.parseInt(params[0]), params[3]);
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
        currentServer.addHandler(PurchaseControllerService.BOOKS_WITH_HIGHEST_PURCHASE_COUNT, (request) -> {
            Future<List<String>> future = purchaseControllerHandler.books_with_highest_purchase_count();
            future.isDone();
            try {
                List<String> filteredBooks = future.get();
                String response = "";
                for(String string: filteredBooks){
                    response += string + "\n";
                }
                return new Message("ok", response); //fixme: hardcoded str
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message("error", ""); //fixme: hardcoded str
            }
        });
        currentServer.addHandler(PurchaseControllerService.BOOKS_WITH_HIGHEST_PURCHASE_COUNT_PER_GENRE, (request) -> {
            Future<List<String>> future = purchaseControllerHandler.books_with_highest_purchase_count_per_genre();
            future.isDone();
            try {
                List<String> books = future.get();
                String response = "";
                for(String string: books){
                    response += string + "\n";
                }
                return new Message("ok", response); //fixme: hardcoded str
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message("error", ""); //fixme: hardcoded str
            }
        });
        currentServer.addHandler(PurchaseControllerService.CLIENTS_WITH_MOST_PURCHASES, (request) -> {
            Future<List<String>> future = purchaseControllerHandler.clients_with_most_purchases();
            future.isDone();
            try {
                List<String> books = future.get();
                String response = "";
                for(String string: books){
                    response += string + "\n";
                }
                return new Message("ok", response); //fixme: hardcoded str
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message("error", ""); //fixme: hardcoded str
            }
        });

            return currentServer;
    }

}
