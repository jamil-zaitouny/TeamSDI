package springjpa.Model;

import org.w3c.dom.Element;

import javax.persistence.Entity;
import java.util.Optional;

@Entity
public class Purchase extends BaseEntity<Integer>
{
    private String bookId;
    private int clientId;
    private String purcahseDetails;

    public Purchase()
    {}
    public Purchase(int purchaseID, String bookId, int clientId,String purcahseDetails) {
        super.setId(purchaseID);
        this.bookId = bookId;
        this.clientId = clientId;
        this.purcahseDetails=purcahseDetails;
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
        return "PurchaseID: "+ this.getId() + "\n" +
                "Book: " + bookId + "\n"+
                "Client: " + clientId + "\n"+
                "PurchaseDetails: "+purcahseDetails+"\n" ;
    }

    @Override
    public boolean equals(Object obj) {
        Optional<Object> objectOptional = Optional.ofNullable(obj).filter(v -> v instanceof Purchase);
        return objectOptional
                .filter(v->((Purchase) v).getId().equals(this.getId()))
                .isPresent();
    }

}
