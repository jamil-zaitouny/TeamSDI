//package Server;
//
//import Controller.BookController;
//import Controller.ClientController;
//import Controller.PurchaseController;
//import Model.Book;
//import Model.Client;
//import Model.Purchase;
//import Repository.DBRepository.BookDBRepository;
//import Repository.DBRepository.ClientDBRepository;
//import Repository.DBRepository.PurchaseDBRepository;
//import Repository.SortRepository.SortingRepository;
//import Server.TCP.FillMethodHandler;
//import Server.TCP.TCPServer;
//import Server.service.BookControllerHandler;
//import Server.service.ClientControllerHandler;
//import Server.service.PurchaseControllerHandler;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//public class ServerApp {
//    public static void main(String[] args){
//        try{
//            ExecutorService executorService = Executors.newFixedThreadPool(
//                    Runtime.getRuntime().availableProcessors());
//
//        String isbn = "1234567890999";
//        String title = "Fram, ursul polar";
//        String author = "Cezar Petrescu";
//        String genre = "fiction";
//
//        String directory = ".\\src\\main\\java\\Files\\";
//        String xmlDirectory = ".\\src\\main\\java\\Files\\";
//        Book book = new Book(isbn, title, author, genre);
//
//        SortingRepository<Integer, Purchase> purchaseRepo = new PurchaseDBRepository();
//        SortingRepository<String, Book> bookRepo = new BookDBRepository();
//        SortingRepository<Integer, Client> clientRepo = new ClientDBRepository();
//
//        ClientController clientController = new ClientController(clientRepo);
//        BookController bookController = new BookController(bookRepo);
//        PurchaseController purchaseController=new PurchaseController(purchaseRepo,clientController,bookController);
//
//        BookControllerHandler bookControllerHandler = new BookControllerHandler(executorService, bookController);
//        ClientControllerHandler clientControllerHandler = new ClientControllerHandler(executorService, clientController);
//        PurchaseControllerHandler purchaseControllerHandler = new PurchaseControllerHandler(executorService, purchaseController);
//
//        TCPServer tcpServer = new TCPServer(executorService);
//        tcpServer = FillMethodHandler.fillBookMethods(tcpServer, bookControllerHandler);
//        tcpServer = FillMethodHandler.fillClientMethods(tcpServer, clientControllerHandler);
//        tcpServer = FillMethodHandler.fillPurchaseMethods(tcpServer, purchaseControllerHandler);
//        System.out.println("Starting Server");
//        tcpServer.startServer();
//
//            executorService.shutdown();
//        } catch (RuntimeException ex) {
//            ex.printStackTrace();
//        }
//
//    }
//}

