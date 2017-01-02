package myDomXmlParser;

import org.xml.sax.*;
import org.w3c.dom.*;

import java.io.IOException;

import javax.xml.parsers.*;


public class mySaxParser {

	public static void main(String[] args) {
		// Parse XML file input
		Document xmlDoc = getDocument("people.xml");
		
		System.out.println("Root: " + xmlDoc.getDocumentElement().getNodeName());
		
		NodeList personList = xmlDoc.getElementsByTagName("person");
		
		System.out.println("Number of people : " + personList.getLength());
		
		String firstNameElement = "firstname";
		String lastNameElement = "lastname";
		String idAttrElement = "id";
		
		getElementsAndAttrib(personList, firstNameElement, lastNameElement, idAttrElement);
	}

	private static void getElementsAndAttrib(NodeList personList, String firstNameElementString, String lastNameElementString,
			String idAttrElement) {
		
		try {
			for (int i=0; i < personList.getLength(); i++) {
				Node personNode = personList.item(i);
				
				Element personElement = (Element) personNode;
				
				// Retrieve First Name from Person
				NodeList firstNameList = personElement.getElementsByTagName(firstNameElementString);
				// Get the First Name XML node
				Element firstNameElement = (Element) firstNameList.item(0);
				// Get the First Name XML value node
				NodeList firstNameValueList = firstNameElement.getChildNodes();
				
				// Retrieve Last Name from Person
				NodeList lastNameList = personElement.getElementsByTagName(lastNameElementString);
				// Get the Last Name XML node
				Element lastNameElement = (Element) lastNameList.item(0);
				// Get the Last Name XML value node
				NodeList lastNameValueList = lastNameElement.getChildNodes();
				
				// Retrieve ID attribute from Person
				if (personElement.hasAttribute(idAttrElement)) {
					System.out.println("Person ID : " + personElement.getAttribute(idAttrElement) + "\n" + 
									firstNameElementString + " : " + firstNameValueList.item(0).getNodeValue().trim() + "\n" + 
									lastNameElementString + " : " + lastNameValueList.item(0).getNodeValue().trim());
				} else {
					System.out.println("Person ID : UNKNOWN" + "\n" + 
							firstNameElementString + " : " + firstNameValueList.item(0).getNodeValue().trim() + "\n" + 
							lastNameElementString + " : " + lastNameValueList.item(0).getNodeValue().trim());
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
