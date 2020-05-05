package web.converter;

import core.model.Purchase;
import org.springframework.stereotype.Component;
import web.dto.PurchaseDto;

@Component
public class PurchaseConverter extends BaseConverter<Integer, Purchase, PurchaseDto>{
    @Override
    public Purchase convertDtoToModel(PurchaseDto dto) {
        Purchase purchase=Purchase.builder()
                .bookId(dto.getBookId()).clientId(dto.getClientId()).purcahseDetails(dto.getPurcahseDetails()).build();
        purchase.setId(dto.getId());
        return purchase;
    }

    @Override
    public PurchaseDto convertModelToDto(Purchase purchase) {
        PurchaseDto dto=PurchaseDto.builder().bookId(purchase.getBookId()).clientId(purchase.getClientId())
                .purcahseDetails(purchase.getPurcahseDetails()).build();
        dto.setId(purchase.getId());
        return dto;
    }
}
