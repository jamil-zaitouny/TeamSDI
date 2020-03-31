package Server.TCP;

import Common.Communication.Message;
import Common.HandlerServices.BookControllerService;
import Common.HandlerServices.ClientControllerService;
import Common.HandlerServices.PurchaseControllerService;
import Model.Book;
import Model.Client;
import Model.Purchase;
import Server.service.BookControllerHandler;
import Server.service.ClientControllerHandler;
import Server.service.PurchaseControllerHandler;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

//Fills the
public class FillMethodHandler {

    public static TCPServer fillWithAllMethods(TCPServer server) {
        return server;
    }

    public static TCPServer fillBookMethods(TCPServer server, BookControllerHandler bookControllerHandler) {
        TCPServer currentServer = server;
        currentServer.addHandler(BookControllerService.ADD_BOOK, (request) -> {
            String[] params = ((String) request.getBody()).split(" ");
            Future<Void> future = bookControllerHandler.addBook(params[0], params[1], params[2], params[3]);
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
        currentServer.addHandler(BookControllerService.DELETE_BOOK, (request) -> {
            Future<Void> future = bookControllerHandler.deleteBook((String) request.getBody());
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
        currentServer.addHandler(BookControllerService.UPDATE_BOOK, (request) -> {
            String[] params = ((String) request.getBody()).split(" ");
            Future<Void> future = bookControllerHandler.updateBook(params[0], params[1], params[2], params[3]);
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
        currentServer.addHandler(BookControllerService.FILTER_BY_GENRE, (request) -> {
                    String[] params = ((String)request.getBody()).split(" ");
                    Future<Set<Book>> future = bookControllerHandler.filterByGenre(params[0]);
                    future.isDone();
                    try {
                        Set<Book> filteredBooks = future.get();
                        return new Message("ok", (Serializable) filteredBooks); //fixme: hardcoded str
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                        return new Message("error", ""); //fixme: hardcoded str
                    }
                }

        );
        currentServer.addHandler(BookControllerService.PRINT_BOOKS, (request) -> {
            Future<Set<Book>> future = bookControllerHandler.print_books();
            future.isDone();
            try {
                Set<Book> books = future.get();
                return new Message("ok", (Serializable) books); //fixme: hardcoded str
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message("error", ""); //fixme: hardcoded str
            }
        });
        currentServer.addHandler(BookControllerService.SEARCH_BY_ISBN, (request) -> {
            Future<Book> future = bookControllerHandler.searchByIsbn((String)request.getBody());
            future.isDone();
            try {
                Book book = future.get();
                return new Message("ok", book); //fixme: hardcoded str
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            return new Message("error", ""); //fixme: hardcoded str
            }
        });
        return currentServer;
    }

    public static TCPServer fillClientMethods(TCPServer server, ClientControllerHandler clientControllerHandler) {
        TCPServer currentServer = server;
        currentServer.addHandler(ClientControllerService.ADD_CLIENT, (request) -> {
            String[] params = ((String)request.getBody()).split(" ");
            Future<Void> future = clientControllerHandler.addClient(Integer.parseInt(params[0]), params[1]);
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
        currentServer.addHandler(ClientControllerService.DELETE_CLIENT, (request) -> {
            Future<Void> future = clientControllerHandler.deleteClient(Integer.parseInt((String)request.getBody()));
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
        currentServer.addHandler(ClientControllerService.UPDATE_CLIENT, (request) -> {
            String[] params = ((String)request.getBody()).split(" ");
            Future<Void> future = clientControllerHandler.updateClient(Integer.parseInt(params[0]), params[1]);
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
        currentServer.addHandler(ClientControllerService.FILTER_BY_Name, (request) -> {
            Future<Set<Client>> future = clientControllerHandler.filterByName((String)request.getBody());
            future.isDone();
            try {
                Set<Client> filteredClients = future.get();
                return new Message("ok", (Serializable) filteredClients); //fixme: hardcoded str
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
                return new Message("ok", (Serializable) clients); //fixme: hardcoded str
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            return new Message("error", ""); //fixme: hardcoded str
            }
        });
        currentServer.addHandler(ClientControllerService.SEARCH_BY_ID, (request) -> {
            Future<Client> future = clientControllerHandler.searchById(Integer.parseInt((String)request.getBody()));
            future.isDone();
            try {
                Client client = future.get();
                return new Message("ok", client); //fixme: hardcoded str
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            return new Message("error", ""); //fixme: hardcoded str
             }
        });
        return currentServer;
    }

    public static TCPServer fillPurchaseMethods(TCPServer server, PurchaseControllerHandler purchaseControllerHandler) {
        TCPServer currentServer = server;
        currentServer.addHandler(PurchaseControllerService.ADD_PURCHASE, (request) -> {
            String[] params = ((String)request.getBody()).split(" ");
            Future<Void> future = purchaseControllerHandler.addPurchase(new Purchase(Integer.parseInt(params[0]),params[1],Integer.parseInt(params[2]), params[3]));
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
        currentServer.addHandler(PurchaseControllerService.DELETE_PURCHASE, (request) -> {
            Future<Void> future = purchaseControllerHandler.deletePurchase(Integer.parseInt((String)request.getBody()));
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
        currentServer.addHandler(PurchaseControllerService.UPDATE_PURCHASE, (request) -> {
            String[] params = ((String)request.getBody()).split(" ");
            Future<Void> future = purchaseControllerHandler.updatePurchase(Integer.parseInt(params[0]), params[3]);
            future.isDone();
            return new Message("ok", ""); //fixme: hardcoded str
        });
        currentServer.addHandler(PurchaseControllerHandler.PRINT_PURCHASES, (request) -> {
            Future<Set<Purchase>> future = purchaseControllerHandler.printPurchases();
            future.isDone();
            try {
                Set<Purchase> purchases = future.get();
                return new Message("ok", (Serializable) purchases); //fixme: hardcoded str
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message("error", ""); //fixme: hardcoded str
            }
        });
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
