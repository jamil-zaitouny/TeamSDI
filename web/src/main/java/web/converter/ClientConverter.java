package web.converter;

import core.model.Client;
import org.springframework.stereotype.Component;
import web.dto.ClientDto;

@Component
public class ClientConverter extends BaseConverter<Integer, Client, ClientDto>{
    @Override
    public Client convertDtoToModel(ClientDto dto) {
        Client client=Client.builder().name(dto.getName()).build();
        client.setId(dto.getId());
        return client;
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto dto=ClientDto.builder().name(client.getName()).build();
        dto.setId(client.getId());
        return dto;
    }
}
