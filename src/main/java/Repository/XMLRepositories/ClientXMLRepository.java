package Repository.XMLRepositories;

import Model.Client;
import Model.Exceptions.ValidatorException;
import Repository.RepositoryInMemory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ClientXMLRepository extends RepositoryInMemory<Integer, Client> {
    private String directory;
    private String fileName;


    public ClientXMLRepository(String directory) throws IOException, SAXException, ParserConfigurationException {
        super();
        this.directory = directory;
        this.fileName = "clients.xml";
        this.directory += this.fileName;
        loadXML();
    }
    public ClientXMLRepository(String directory, String fileName) throws IOException, SAXException, ParserConfigurationException {
        super();
        this.directory = directory;
        this.fileName = fileName;
        this.directory += this.fileName;
        loadXML();
    }

    private void loadXML() throws ParserConfigurationException, IOException, SAXException {
        Document documnet = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .parse(this.directory);

        Element root = documnet.getDocumentElement();
        NodeList children = root.getChildNodes();
        IntStream
                .range(0, children.getLength())
                .mapToObj(index -> children.item(index))
                .filter(node -> node instanceof Element)
                .forEach(node -> {
                    try {
                        add(new Client((Element) node));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
    public void saveClient(Client client) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Document document = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .parse(directory);

        Element root = document.getDocumentElement();
        Node bookNode = toXML(document, client);
        root.appendChild(bookNode);

        Transformer transformer = TransformerFactory
                .newInstance()
                .newTransformer();
        transformer.transform(new DOMSource(document),
                new StreamResult(new File(directory)));
    }

    @Override
    public Optional<Client> add(Client entity) throws ValidatorException, IOException {
        XMLUtilities.resetXML(directory);
        Optional optional = super.add(entity);
        this.findAll().forEach(client -> {
            try {
                saveClient(client);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        });
        return optional;
    }

    @Override
    public Optional<Client> delete(Integer id) {
        XMLUtilities.resetXML(directory);
        Optional optional = super.delete(id);
        this.findAll().forEach(book ->
        {
            try {
                saveClient(book);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        });
        return optional;
    }

    @Override
    public Optional<Client> update(Client entity) throws ValidatorException {
        XMLUtilities.resetXML(directory);
        Optional optional = super.update(entity);
        this.findAll().forEach(book ->
        {
            try {
                saveClient(book);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        });
        return optional;
    }
    public Node toXML(Document document, Client client) {
        Element clientElement = document.createElement("client");
        XMLUtilities.appendChildWithTextToNode(document, clientElement, "id", String.valueOf(client.getId()));
        XMLUtilities.appendChildWithTextToNode(document, clientElement, "name", client.getName());
        return clientElement;
    }
}
