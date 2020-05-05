package bookshop.web.dto.Model;

import bookshop.web.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class PurchaseDto extends BaseDto<Integer> {
    public String bookId;
    public int clientId;
    public String purchaseDetails;
    public PurchaseDto(int id, String ISBN, int clientID, String purchaseDetails){
        this.id = id;
        this.bookId = ISBN;
        this.clientId = clientID;
        this.purchaseDetails = purchaseDetails;
    }
}
