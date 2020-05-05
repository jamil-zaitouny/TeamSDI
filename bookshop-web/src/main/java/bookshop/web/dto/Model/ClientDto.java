package bookshop.web.dto.Model;

import bookshop.web.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class ClientDto extends BaseDto<Integer> {
    private String name;
    public ClientDto(int id, String name){
        super(id);
        this.name = name;
    }
}
