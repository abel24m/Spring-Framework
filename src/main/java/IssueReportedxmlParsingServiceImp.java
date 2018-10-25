import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class IssueReportedxmlParsingServiceImp implements xmlParsingService {

	public int calculateIssue(String issue) {
		int counter = 0;
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = builderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		Document document = null;
		try {
			document = builder
					.parse(new URL("http://www.cs.utexas.edu/~devdatta/traffic_incident_data.xml").openStream());
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Element rootElement = document.getDocumentElement();
		Queue<Element> q = new LinkedList<Element>();
		q.add(rootElement);
		while (!q.isEmpty()) {
			Element e = (Element) q.remove();
			if (e.getNodeName().equals("ds:issue_reported")) {
				String nodeValue = e.getTextContent();
				if (nodeValue.equalsIgnoreCase(issue)) {
					counter++;
				}
			}
			NodeList nodes = e.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node instanceof Element) {
					q.add((Element) node);
				}
			}
		}
		return counter;
	}

	public String categorizeIssue(int numOfIncidents) {
		String riskLevel;
		if (numOfIncidents < 6)
			riskLevel = "Rare";
		else if (numOfIncidents < 11)
			riskLevel = "Very Low";
		else if (numOfIncidents < 16)
			riskLevel = "Low";
		else if (numOfIncidents < 31)
			riskLevel = "Medium";
		else if (numOfIncidents < 51)
			riskLevel = "Moderate";
		else if (numOfIncidents < 101)
			riskLevel = "High";
		else if (numOfIncidents < 201)
			riskLevel = "Very High";
		else
			riskLevel = "Extreme";
		return " Risk Level: " + riskLevel + ".";

	}
}
