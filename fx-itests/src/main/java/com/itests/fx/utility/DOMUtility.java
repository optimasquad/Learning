package com.itests.fx.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
//import org.apache.xerces.dom.CoreDocumentImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DOMUtility {
	public static File requestXsd = new File(
			"C:\\CCViews\\fx4_dev\\usr1\\arbor\\fx_mw_src\\bali\\JavaIF\\docs\\xsd\\request.xsd");

	public static Document xmlStringToDocument(String xmlSource)
			throws SAXException, ParserConfigurationException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(xmlSource)));
		return doc;
	}

	public static Document xmlFileToDocument(String filePath)
			throws SAXException, ParserConfigurationException, IOException {
		// Create DOM document builder
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder domBuilder = domFactory.newDocumentBuilder();

		// Convert XML file into a DOM Document
		Document domDoc = domBuilder.parse(new FileInputStream(filePath));

		// Return the DOM Document
		return domDoc;
	}

	public static String convertDocumentToString(Document doc) {
		String output = null;
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			output = writer.getBuffer().toString().replaceAll("\n|\r", "");
		} catch (Exception e) {
		}

		return output;
	}

	/**
	 * Utility method for printing DOMs.
	 * 
	 * @param doc
	 *            DOM Document to be printed.
	 */
	public static String DOM2String(Document doc) {
		StringWriter output = new StringWriter();
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			if (doc.getDoctype() != null) {
				String systemValue = (new File(doc.getDoctype().getSystemId())).getName();
				transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, systemValue);
			}
			transformer.transform(new DOMSource(doc), new StreamResult(output));
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		// System.out.println(output.toString());
		// return "<![CDATA["+output.toString()+"]]>";//Avoid parsing
		return output.toString();
	}

	/**
	 * Utility method for printing DOMs.
	 * 
	 * @param doc
	 *            DOM Document to be printed.
	 */
	public static String printDOM(Document doc, String docType) throws IOException {
		System.out.println("\n" + docType);
		System.out.println("========");
		return printDOM(doc);
	}

	/**
	 * Utility method for printing DOMs.
	 * 
	 * @param doc
	 *            DOM Document to be printed.
	 */
	public static String printDOM(Document doc) throws IOException {
		OutputFormat format = new OutputFormat();
		format.setIndent(2);
		XMLSerializer xs = new XMLSerializer(format);
		xs.setOutputByteStream(System.out);
		xs.serialize(doc);

		return xs.toString();
	}

	/**
	 * Get a node from a document based on its name
	 * 
	 * @param doc
	 * @param nodeName
	 * @return
	 */
	public static Node getNodeByName(Document doc, String nodeName) {
		Element docElement = doc.getDocumentElement();
		// The dynamic param list holds the path to the dynamic parameters
		NodeList paramList = docElement.getElementsByTagName(nodeName);
		// if required element found return first instance
		if (paramList != null && paramList.getLength() > 0) {
			return (paramList.item(0));
		}
		return null;
	}

	/**
	 * Get a node list from a document based on its name
	 * 
	 * @param doc
	 * @param nodeName
	 * @return
	 */
	public static NodeList getNodesByName(Document doc, String nodeName) {
		Element docElement = doc.getDocumentElement();
		// The dynamic param list holds the path to the dynamic parameters
		return docElement.getElementsByTagName(nodeName);
	}

	/**
	 * Validates an XML against an XSD
	 * 
	 * @param xsdFile
	 *            The XSD to validate with
	 * @param dom
	 *            The XML to validate
	 * @throws SAXException
	 * @throws IOException
	 */
	public static void validateDOM(File xsdFile, Document dom) throws SAXException, IOException {
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Source schemaFile = new StreamSource(xsdFile); // new
														// File("C:\\CCViews\\fx4_dev\\usr1\\arbor\\fx_mw_src\\bali\\JavaIF\\docs\\xsd\\request.xsd"));
		Schema schema = schemaFactory.newSchema(schemaFile);
		Validator validator = schema.newValidator();
		validator.validate(new DOMSource(dom));
	}
}
