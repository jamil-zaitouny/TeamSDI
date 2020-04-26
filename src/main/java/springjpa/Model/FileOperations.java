package springjpa.Model;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public interface FileOperations {
    public String[] toCSV();
    public Node toXML(Document document);
}
