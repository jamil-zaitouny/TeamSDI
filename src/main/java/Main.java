import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

    public static void main(String[] args) {
        System.out.println("Application Running!");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
            "springjpa"
        );
    }


//    public static void main(String[] args) throws IOException, TransformerException, ParserConfigurationException {
//        String isbn = "1234567890999";
//        String title = "Fram, ursul polar";
//        String author = "Cezar Petrescu";
//        String genre = "fiction";
//
////        String directory = ".\\src\\main\\java\\Files\\";
////        String xmlDirectory = ".\\src\\main\\java\\Files\\";
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
//        Console console = new Console(clientController, bookController,purchaseController);
//        console.run();
//    }
}
