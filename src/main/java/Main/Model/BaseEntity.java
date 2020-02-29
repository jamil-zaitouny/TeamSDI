package Main.Model;

public class BaseEntity<IDType> {
    private IDType id;

    BaseEntity(IDType id){
        this.id = id;
    }
    IDType getId() {
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
