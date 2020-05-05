package web.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class PurchaseDto extends BaseDto<Integer>{
    private String bookId;
    private int clientId;
    private String purcahseDetails;
}
