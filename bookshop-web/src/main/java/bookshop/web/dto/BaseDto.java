package bookshop.web.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseDto<IDType extends Serializable> implements Serializable {
    public IDType id;
}
