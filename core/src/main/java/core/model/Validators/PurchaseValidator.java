package core.model.Validators;

import org.springframework.stereotype.Component;
import core.model.Exceptions.ValidatorException;
import core.model.Purchase;

import java.util.Optional;

@Component
public class PurchaseValidator implements IValidator<Purchase>
{
    @Override
    public void validate(Purchase purchase) throws ValidatorException {
        Optional<Purchase> purchaseOptional = Optional.ofNullable(purchase);
        purchaseOptional.filter(v -> v.getClientId() > 0).orElseThrow(() -> new ValidatorException("Invalid Client ID"));
        purchaseOptional.filter(v -> v.getBookId().length()>0).orElseThrow(() -> new ValidatorException("Invalid Book ID"));
    }
}
