package springjpa.Repository.XMLRepositories;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class XMLUtilities {
    public static void appendChildWithTextToNode(Document document,
                                                 Node parentNode,
                                                 String tagName,
                                                 String textContent) {
        Element element = document.createElement(tagName);
        element.setTextContent(textContent);
        parentNode.appendChild(element);
    }

    public static String getTextFromTagName(Element parentElement, String tagName){
        Node node = parentElement.getElementsByTagName(tagName).item(0);
        return node.getTextContent();
    }
    public static void resetXML(String directory){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File(directory));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.print("");
        writer.print("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n\t<elements>\n\t</elements>");
        writer.close();

    }
}
