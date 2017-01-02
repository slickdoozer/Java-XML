package myDomXmlParser;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class myXPathParser {

	public static void main(String[] args){
		// Get a parser that turns XML doc into a DOM tree
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		
		// Provides support for XML namespaces if needed
		domFactory.setNamespaceAware(true);
		
		// Convert XML into a DOM tree
		DocumentBuilder builder;
		Document doc = null;
		
		try {
			// Parse the supplied file
			builder = domFactory.newDocumentBuilder();
			doc = builder.parse("tvshows.xml");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Grab data from document based on XPath
		XPath xpath = XPathFactory.newInstance().newXPath();
		getNodeNameAndValue(doc, xpath);
		
	}

	private static void getNodeNameAndValue(Document doc, XPath xpath) {
		
		// XPath query
		XPathExpression expr;
		Object result = null;
		
		try {
			
			// Fetch only US-based networks
			expr = xpath.compile("//show/network[@country='US']//text()");
			
			// Fetch every node
			//expr = xpath.compile("//show/*//text()");
			
			// Fetch every show name
			//expr = xpath.compile("//show/name//text()");
			
			// Fetch every show name
			//expr = xpath.compile("//show/name//text()");
			
			// Returns the result of the query
			result = expr.evaluate(doc, XPathConstants.NODESET);
			
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Output result
		NodeList nodes = (NodeList) result;
		for (int i = 0; i < nodes.getLength(); i++) {
			System.out.println(nodes.item(i).getParentNode().getNodeName() + " = " + nodes.item(i).getNodeValue());
		}
		
	}
	
}
