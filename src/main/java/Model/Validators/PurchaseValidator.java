package Model.Validators;

import Model.Exceptions.ValidatorException;
import Model.Purchase;

public class PurchaseValidator implements IValidator<Purchase>
{
    @Override
    public void validate(Purchase purchase) throws ValidatorException {
        if(purchase.getClientId()<0)
            throw new ValidatorException("Invalid client id");
        else if (purchase.getBookId().length()!=13)
            throw new ValidatorException("Invalid book ISBN");
    }
}
