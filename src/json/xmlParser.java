package json;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xmlParser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		xmlParser.parseXMLDOM();
	}

	public static void parseXMLDOM() {
		try {

			File fXmlFile = new File("./file/staff.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :"
					+ doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("staff");

			System.out.println("----------------------------");

			for (int i = 0; i < nList.getLength(); i++) {

				Node nNode = nList.item(i);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					System.out.println("Staff id : "
							+ eElement.getAttribute("id"));
					System.out.println("First Name : "
							+ eElement.getElementsByTagName("firstname")
									.item(0).getTextContent());
					System.out.println("Last Name : "
							+ eElement.getElementsByTagName("lastname").item(0)
									.getTextContent());
					System.out.println("Nick Name : "
							+ eElement.getElementsByTagName("nickname").item(0)
									.getTextContent());
					System.out.println("Salary : "
							+ eElement.getElementsByTagName("salary").item(0)
									.getTextContent());

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
