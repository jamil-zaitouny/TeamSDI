package Model.Validators;

import Model.Client;
import Model.Exceptions.ValidatorException;
import javafx.util.Pair;

import java.util.List;

public class ClientValidator implements IValidator<Client> {

    @Override
    public void validate(Client client) throws ValidatorException {
        if(client.getId() < 0){
            throw new ValidatorException("ID can't be negative!");
        }else if(client.getName().length() > 50){
            throw new ValidatorException("Client name character limit exceeded!");
        }
    }
}
