package springjpa.Repository.XMLRepositories;

import springjpa.Model.Book;
import springjpa.Model.Exceptions.ValidatorException;
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
import java.io.*;
import java.util.Optional;
import java.util.stream.IntStream;

public class BookXMLRepository extends RepositoryInMemory<String, Book> {
    private String directory;
    private String fileName;


    public BookXMLRepository(String directory) throws IOException, SAXException, ParserConfigurationException {
        super();
        this.directory = directory;
        this.fileName = "books.xml";
        this.directory += this.fileName;
        loadXML();
    }
    public BookXMLRepository(String directory, String fileName) throws IOException, SAXException, ParserConfigurationException {
        super();
        this.directory = directory;
        this.fileName = fileName;
        this.directory += this.fileName;
        loadXML();
    }
    public Node toXML(Document document, Book book) {
        Element bookElement = document.createElement("book");
        XMLUtilities.appendChildWithTextToNode(document, bookElement, "isbn", book.getId());
        XMLUtilities.appendChildWithTextToNode(document, bookElement, "title", book.getTitle());
        XMLUtilities.appendChildWithTextToNode(document, bookElement, "author", book.getAuthorName());
        XMLUtilities.appendChildWithTextToNode(document, bookElement, "genre", book.getGenre());
        return bookElement;
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
                        add(new Book((Element) node));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    public void saveBook(Book book) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Document document = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .parse(directory);

        Element root = document.getDocumentElement();
        Node bookNode = toXML(document, book);
        root.appendChild(bookNode);

        Transformer transformer = TransformerFactory
                .newInstance()
                .newTransformer();
        transformer.transform(new DOMSource(document),
                new StreamResult(new FileWriter(new File(directory))));
    }


    @Override
    public Optional<Book> add(Book entity) throws ValidatorException, IOException {
        XMLUtilities.resetXML(directory);
        Optional optional = super.add(entity);
            this.findAll().forEach(book -> {
                try {
                    saveBook(book);
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
    public Optional<Book> delete(String s) {
        XMLUtilities.resetXML(directory);
        Optional optional = super.delete(s);
        this.findAll().forEach(book ->
        {
            try {
                saveBook(book);
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
    public Optional<Book> update(Book entity) throws ValidatorException {
        XMLUtilities.resetXML(this.directory);
        Optional optional = super.update(entity);
        this.findAll().forEach(book ->
        {
            try {
                saveBook(book);
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
