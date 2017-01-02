package ReadWriteXML;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class readWriteXMLUsingJDom {

	public static void main(String[] args) {
		
		//writeXMLFile();
		
		readXMLFile();
	}

	private static void readXMLFile() {
		
		SAXBuilder builder = new SAXBuilder();
		
		try {
			// Parse file into a JDOM document
			Document doc = builder.build(new File("jdomMade.xml"));
			
			// Return document root element
			System.out.println("Root: " + doc.getRootElement());
			
			// Get the text found between the name tags
			System.out.println("Show: " + doc.getRootElement().getChild("show").getChildText("name"));
			
			// Get the attribute value for show_id assigned to name
			System.out.println("Show ID: " + doc.getRootElement().getChild("show").getChild("name").getAttributeValue("show_id") + "\n");
			
			// Cycle through all children of the root element
			Element root = doc.getRootElement();
			for (Element currElement : root.getChildren("show")) {
				System.out.println("Show: " + currElement.getChildText("name"));
				System.out.println("Show ID: " + currElement.getChild("name").getAttributeValue("show_id"));
				System.out.println("On " + currElement.getChild("network") + " in the " + currElement.getChild("network").getAttributeValue("country") + "\n");
			}
			
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void writeXMLFile() {
		
		// Create JDom document
		Document doc = new Document();
		
		// Create root element named 'tvshows'
		Element theRoot = new Element("tvshows");
		doc.setRootElement(theRoot);
		
		// Create and assign FIRST SHOW sub-elements
		Element show = new Element("show");
		
			Element name = new Element("name");
			name.setAttribute("show_id", "show_001");
			name.addContent("Life On Mars");
			
			Element network = new Element("network");
			network.setAttribute("country", "US");
			network.addContent("ABC");
			
			// Assign 'name/network' to 'show' element
			show.addContent(name);
			show.addContent(network);
			
			// Assign 'show' element to 'tvshows' root element
			theRoot.addContent(show);
		
		// Create and assign SECOND SHOW sub-elements
		Element show2 = new Element("show");
			
			Element name2 = new Element("name");
			name2.setAttribute("show_id", "show_002");
			name2.addContent("Freaks And Geeks");
			
			Element network2 = new Element("network");
			network2.setAttribute("country", "US");
			network2.addContent("ABC");
			
			// Assign 'name/network' to 'show' element
			show2.addContent(name2);
			show2.addContent(network2);
			
			// Assign 'show' element to 'tvshows' root element
			theRoot.addContent(show2);
		
		// Use pretty output formatter with indenting
		XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
		
		// Create new XML file
		try {
			xmlOutput.output(doc, new FileOutputStream(new File("jdomMade.xml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Wrote to file");
	}
	
}