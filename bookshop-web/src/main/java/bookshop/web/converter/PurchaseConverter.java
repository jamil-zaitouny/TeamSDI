package bookshop.web.converter;

import bookshop.core.model.Purchase;
import bookshop.web.dto.Model.PurchaseDto;
import org.springframework.stereotype.Component;

@Component
public class PurchaseConverter implements Converter<Purchase, PurchaseDto>{

    @Override
    public Purchase convertDtoToModel(PurchaseDto dto) {
        return new Purchase(dto.getId(), dto.getBookId(), dto.getClientId(), dto.getPurchaseDetails());
    }

    @Override
    public PurchaseDto convertModelToDto(Purchase purchase) {
        return new PurchaseDto(purchase.getId(), purchase.getBookId(), purchase.getClientId(), purchase.getPurcahseDetails());
    }
}
