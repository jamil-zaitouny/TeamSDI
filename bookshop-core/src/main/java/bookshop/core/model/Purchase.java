package bookshop.core.model;

import lombok.*;

import javax.persistence.Entity;
import java.util.Optional;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class Purchase extends BaseEntity<Integer>
{
    private String bookId;
    private int clientId;
    private String purcahseDetails;

    public Purchase(int id, String bookId, int clientID, String purcahseDetails){
        super(id);
        this.bookId = bookId;
        this.clientId = clientID;
        this.purcahseDetails = purcahseDetails;
    }
}
