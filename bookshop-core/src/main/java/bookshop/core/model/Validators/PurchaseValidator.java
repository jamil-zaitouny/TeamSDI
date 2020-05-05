package bookshop.core.model.Validators;

import bookshop.core.model.Exceptions.ValidatorException;
import bookshop.core.model.Purchase;
import org.springframework.stereotype.Component;

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
