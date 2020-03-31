package Model;

import Repository.XMLRepositories.XMLUtilities;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.sql.rowset.spi.XmlReader;
import java.io.Serializable;
import java.util.Optional;

public class Purchase extends BaseEntity<Integer> implements Serializable{
    private String bookId;
    private int clientId;
    private String purcahseDetails;

    public Purchase(int purchaseID, String bookId, int clientId, String purcahseDetails) {
        super(purchaseID);
        this.bookId = bookId;
        this.clientId = clientId;
        this.purcahseDetails = purcahseDetails;
    }

    public Purchase(Element purchaseElement) {
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

    public String getPurcahseDetails() {
        return purcahseDetails;
    }

    public void setPurcahseDetails(String purcahseDetails) {
        this.purcahseDetails = purcahseDetails;
    }

    @Override
    public String toString() {
        return "<PurchaseID: " + this.getId() + "\n" +
                "Book: " + bookId + "\n" +
                "Client: " + clientId + "\n" +
                "PurchaseDetails: " + purcahseDetails + ">";
    }

    @Override
    public boolean equals(Object obj) {
        Optional<Object> objectOptional = Optional.ofNullable(obj).filter(v -> v instanceof Purchase);
        return objectOptional
                .filter(v -> ((Purchase) v).getId().equals(this.getId()))
                .isPresent();
    }

}
