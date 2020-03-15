package Model.Validators;

import Model.Client;
import Model.Exceptions.ValidatorException;

import java.util.List;
import java.util.Optional;

public class ClientValidator implements IValidator<Client> {

    @Override
    public void validate(Client client) throws ValidatorException {
        Optional<Client> clientOptional = Optional.ofNullable(client);
        clientOptional.filter(v -> v.getId() > 0).orElseThrow(()->new ValidatorException("The ID shouldn't be negative!"));
        clientOptional.filter(v -> v.getName().length() < 50).orElseThrow(()->new ValidatorException("Client name character limit exceeded!"));
    }
}
