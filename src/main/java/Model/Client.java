package Model;

import Repository.XMLRepositories.XMLUtilities;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.nio.channels.ClosedByInterruptException;
import java.util.Optional;

public class Client extends BaseEntity<Integer> implements FileOperations{
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

    @Override
    public String[] toCSV() {
        return new String[]{
                String.valueOf(getId()),
                getName()
        };

    }

    @Override
    public Node toXML(Document document) {
        Element clientElement = document.createElement("client");
        XMLUtilities.appendChildWithTextToNode(document, clientElement, "id", String.valueOf(this.getId()));
        XMLUtilities.appendChildWithTextToNode(document, clientElement, "name", this.getName());
        return clientElement;
    }


}
