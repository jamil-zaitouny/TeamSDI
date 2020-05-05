package bookshop.web.dto.Collection;

import bookshop.web.dto.Model.PurchaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PurchasesDto {
    private Set<PurchaseDto> purchases;
}
