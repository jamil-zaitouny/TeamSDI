package Model.Validators;

import Model.Client;
import Model.Exceptions.ValidatorException;
import javafx.util.Pair;

import java.util.List;

public class ClientValidator implements IValidator {
    @Override
    public void validate(Object o) throws ValidatorException {
        if(o instanceof Pair){
            if(
                    ((Pair<List, Client>) o)
                            .getKey()
                            .stream()
                            .noneMatch(v -> v.equals(((Pair<List, Client>) o).getValue())
                            )
            ){
                return;
            }
        }
        throw new ValidatorException("The client already exists in the database!");
    }
}
