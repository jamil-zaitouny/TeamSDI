package springjpa.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity<IDType extends Serializable> {
    @Id
    private IDType id;

    /*BaseEntity(IDType id){this.id = id; }*/
    public IDType getId() {
        return id;
    }

    public void setId(IDType id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
            "id= " + id +
                '}';
    }
}
