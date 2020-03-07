package Model.Validators;

import Model.Exceptions.ValidatorException;
import Model.Purchase;

import java.util.Optional;


public class PurchaseValidator implements IValidator<Purchase>
{
    @Override
    public void validate(Purchase purchase) throws ValidatorException {
        Optional<Purchase> purchaseOptional = Optional.ofNullable(purchase);
        purchaseOptional.filter(v -> v.getClientId() > 0).orElseThrow(() -> new ValidatorException("Invalid Client id"));
        purchaseOptional.filter(v -> v.getBookId().length() == 13).orElseThrow(() -> new ValidatorException("Invalid Client id"));
    }
}
