package bookshop.web.converter;

import bookshop.core.model.Client;
import bookshop.web.dto.Model.ClientDto;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter implements Converter<Client, ClientDto>{

    @Override
    public Client convertDtoToModel(ClientDto dto) {
        return new Client(dto.getId(), dto.getName());
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        return new ClientDto(client.getId(), client.getName());
    }
}
