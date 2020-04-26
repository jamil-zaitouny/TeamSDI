package springjpa.Model;

import springjpa.Repository.XMLRepositories.XMLUtilities;
import org.w3c.dom.Element;

import javax.persistence.Entity;
import java.util.Optional;

@Entity
public class Client extends BaseEntity<Integer>{
    private String name;
    public static String className = "Clients";

    public Client(int id, String name){
        super(id);
        this.name = name;
    }

    public Client(Client copyClient){
        super(copyClient.getId());
        this.name = copyClient.getName();
    }

    public Client(Element clientElement){
        super(Integer.parseInt(XMLUtilities.getTextFromTagName(clientElement, "id")));
        this.name = XMLUtilities.getTextFromTagName(clientElement, "name");
    }

    @Override
    public boolean equals(Object obj) {
        Optional<Object> objectOptional = Optional.ofNullable(obj).filter(v -> v instanceof Client);
        return objectOptional
                .filter(v->((Client) v).getId().equals(this.getId()))
                .isPresent();
    }

    @Override
    public String toString() {
        return "ClientID: " + this.getId() + "\n"
                +"ClientName: " + name + "\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
