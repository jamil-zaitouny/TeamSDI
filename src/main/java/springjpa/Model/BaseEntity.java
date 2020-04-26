package springjpa.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity<IDType> {
    @Id
    @GeneratedValue
    private IDType id;

    BaseEntity(IDType id){
        this.id = id;
    }
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
