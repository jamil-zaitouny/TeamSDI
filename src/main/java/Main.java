import Client.service.BookControllerHandler;
import Client.service.ClientControllerHandler;
import Client.service.PurchaseControllerHandler;
import Common.HandlerServices.BookControllerService;
import Common.HandlerServices.ClientControllerService;
import Common.HandlerServices.PurchaseControllerService;
import Ui.Console;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException, TransformerException, ParserConfigurationException {

//Change controller to the client's respective handler aka clientControllerHandler from the client module (not the one in the server4 module!)
        /*ClientControllerService clientControllerHandler = new ClientControllerHandler();
        BookControllerService bookControllerHandler = new BookControllerHandler();
        PurchaseControllerService purchaseControllerHandler = new PurchaseControllerHandler();

        Console console = new Console(clientControllerHandler, bookControllerHandler, purchaseControllerHandler);
        console.run();
         */
    }
}
