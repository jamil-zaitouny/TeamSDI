package Model;

import Repository.XMLRepositories.XMLUtilities;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.sql.rowset.spi.XmlReader;
import java.util.Optional;

public class Purchase extends BaseEntity<Integer> implements FileOperations
{
    private String bookId;
    private int clientId;

    public Purchase(int purchaseID, String bookId, int clientId) {
        super(purchaseID);
        this.bookId = bookId;
        this.clientId = clientId;
    }
    public Purchase(Element purchaseElement){
        super(Integer.parseInt(XMLUtilities.getTextFromTagName(purchaseElement, "id")));
        this.bookId = XMLUtilities.getTextFromTagName(purchaseElement, "bookid");
        this.clientId = Integer.parseInt(XMLUtilities.getTextFromTagName(purchaseElement, "clientid"));
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "PurchaseID: "+ this.getId() + "\n" +
                "Book: " + bookId + "\n"+
                "Client: " + clientId + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        Optional<Object> objectOptional = Optional.ofNullable(obj).filter(v -> v instanceof Purchase);
        return objectOptional
                .filter(v->((Purchase) v).getId().equals(this.getId()))
                .isPresent();
    }

    @Override
    public String[] toCSV() {
        return (getId() + "," + getBookId() + "," + getClientId()).split(",");
    }

    @Override
    public Node toXML(Document document) {
        Element purchasedElement = document.createElement("purchase");
        XMLUtilities.appendChildWithTextToNode(document, purchasedElement, "id", String.valueOf(this.getId()));
        XMLUtilities.appendChildWithTextToNode(document, purchasedElement, "clientid", String.valueOf(this.getClientId()));
        XMLUtilities.appendChildWithTextToNode(document, purchasedElement, "bookid", this.getBookId());
        return purchasedElement;
    }


}
