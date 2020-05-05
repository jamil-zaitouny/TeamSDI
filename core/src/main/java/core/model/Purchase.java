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
public class Purchase extends BaseEntity<Integer>
{
    private String bookId;
    private int clientId;
    private String purcahseDetails;


}
