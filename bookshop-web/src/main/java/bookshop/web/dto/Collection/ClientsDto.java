package bookshop.web.dto.Collection;

import bookshop.web.dto.Model.ClientDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientsDto {
    private Set<ClientDto> clients;
}
