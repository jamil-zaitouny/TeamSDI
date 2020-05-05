package bookshop.core.model.Validators;

import bookshop.core.model.Client;
import bookshop.core.model.Exceptions.ValidatorException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientValidator implements IValidator<Client> {

    @Override
    public void validate(Client client) throws ValidatorException {
        Optional<Client> clientOptional = Optional.ofNullable(client);
        clientOptional.filter(v -> v.getId() > 0).orElseThrow(()->new ValidatorException("The ID shouldn't be negative!"));
        clientOptional.filter(v -> v.getName().length() < 50).orElseThrow(()->new ValidatorException("Client name character limit exceeded!"));
    }
}
