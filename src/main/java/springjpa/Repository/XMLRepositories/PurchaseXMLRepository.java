package springjpa.Repository.XMLRepositories;

import springjpa.Model.Exceptions.ValidatorException;
import springjpa.Model.Purchase;
import springjpa.Repository.RepositoryInMemory;
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
import java.util.Optional;
import java.util.stream.IntStream;

public class PurchaseXMLRepository extends RepositoryInMemory<Integer, Purchase> {
    private String directory;
    private String fileName;


    public PurchaseXMLRepository(String directory) throws IOException, SAXException, ParserConfigurationException {
        super();
        this.directory = directory;
        this.fileName = "purchases.xml";
        this.directory += this.fileName;
        loadXML();
    }
    public PurchaseXMLRepository(String directory, String fileName) throws IOException, SAXException, ParserConfigurationException {
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
                        add(new Purchase((Element) node));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
    public Node toXML(Document document, Purchase purchase) {
        Element purchasedElement = document.createElement("purchase");
        XMLUtilities.appendChildWithTextToNode(document, purchasedElement, "id", String.valueOf(purchase.getId()));
        XMLUtilities.appendChildWithTextToNode(document, purchasedElement, "clientid", String.valueOf(purchase.getClientId()));
        XMLUtilities.appendChildWithTextToNode(document, purchasedElement, "bookid", purchase.getBookId());
        return purchasedElement;
    }
    public void savePurchase(Purchase purchase) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Document document = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .parse(directory);

        Element root = document.getDocumentElement();
        Node bookNode = toXML(document, purchase);
        root.appendChild(bookNode);

        Transformer transformer = TransformerFactory
                .newInstance()
                .newTransformer();
        transformer.transform(new DOMSource(document),
                new StreamResult(new File(directory)));
    }


    @Override
    public Optional<Purchase> add(Purchase entity) throws ValidatorException, IOException {
        XMLUtilities.resetXML(this.directory);
        Optional optional = super.add(entity);
        this.findAll().forEach(purchase -> {
            try {
                savePurchase(purchase);
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
    public Optional<Purchase> delete(Integer id) {
        XMLUtilities.resetXML(this.directory);
        Optional optional = super.delete(id);
        this.findAll().forEach(book ->
        {
            try {
                savePurchase(book);
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
    public Optional<Purchase> update(Purchase entity) throws ValidatorException {
        XMLUtilities.resetXML(this.directory);
        Optional optional = super.update(entity);
        this.findAll().forEach(book ->
        {
            try {
                savePurchase(book);
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
}
