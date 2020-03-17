package Repository.XMLRepositories;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
}
