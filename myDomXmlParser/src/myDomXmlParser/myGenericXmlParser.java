package myDomXmlParser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class myGenericXmlParser {

	public static void main(String[] args) {
		
		// BEGIN - Configuration section
		String fileName = "people.xml";
		String nodeName = "person";
		String nodeElement1 = "firstname";
		String nodeElement2 = "lastname";
		String attrName = "id";
		// END - Configuration section
		
		// Parse XML file input
		Document xmlDoc = getDocument(fileName);
		
		System.out.println("Root node: " + xmlDoc.getDocumentElement().getNodeName());
		
		NodeList nodeList = xmlDoc.getElementsByTagName(nodeName);
		
		System.out.println("Number of sub-nodes : " + nodeList.getLength() + "\n");
		
		getElementsAndAttrib(nodeList, nodeElement1, nodeElement2, attrName);
	}

	private static void getElementsAndAttrib(NodeList personList, String nodeElement1String, String nodeElement2String,
			String attrElement) {
		
		try {
			for (int i=0; i < personList.getLength(); i++) {
				Node mainNode = personList.item(i);
				
				Element mainElement = (Element) mainNode;
				
				// Retrieve Element 1 from Node
				NodeList element1List = mainElement.getElementsByTagName(nodeElement1String);
				// Get the Element 1 XML node
				Element element1 = (Element) element1List.item(0);
				// Get the Element 1 value node
				NodeList element1ValueList = element1.getChildNodes();
				
				// Retrieve Element 2 from Node
				NodeList element2List = mainElement.getElementsByTagName(nodeElement2String);
				// Get the Element 2 XML node
				Element element2 = (Element) element2List.item(0);
				// Get the Element 2 value node
				NodeList element2ValueList = element2.getChildNodes();
				
				// Retrieve attribute from main element
				if (mainElement.hasAttribute(attrElement)) {
					System.out.println("Attribute ID : " + mainElement.getAttribute(attrElement) + "\n" + 
									nodeElement1String + " : " + element1ValueList.item(0).getNodeValue().trim() + "\n" + 
									nodeElement2String + " : " + element2ValueList.item(0).getNodeValue().trim() + "\n");
				} else {
					System.out.println("Attribute ID : UNKNOWN" + "\n" + 
							nodeElement1String + " : " + element1ValueList.item(0).getNodeValue().trim() + "\n" + 
							nodeElement2String + " : " + element2ValueList.item(0).getNodeValue().trim() + "\n");
				}
			}
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}

	private static Document getDocument(String docString) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(true);
		factory.setValidating(true);
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.parse(new InputSource(docString));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;

	}
	
}
