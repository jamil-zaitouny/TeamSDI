package core.model;

import lombok.*;

import javax.persistence.Entity;
import java.util.Optional;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Client extends BaseEntity<Integer>{
    private String name;

}
