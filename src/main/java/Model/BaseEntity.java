package Model;

import java.io.Serializable;

public class BaseEntity<IDType> implements Serializable {
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
