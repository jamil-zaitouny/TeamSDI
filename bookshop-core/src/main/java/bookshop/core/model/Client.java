package bookshop.core.model;

import lombok.*;

import javax.persistence.Entity;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class Client extends BaseEntity<Integer>{
    private String name;
    public Client(int id, String name){
        super(id);
        this.name = name;
    }
}
