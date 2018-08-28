package com.itests.fx.ppv.itests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.csgsystems.aruba.connection.BSDMSessionContext;
import com.csgsystems.aruba.connection.BSDMSettings;
import com.csgsystems.aruba.connection.Connection;
import com.csgsystems.aruba.connection.ConnectionFactory;
import com.csgsystems.aruba.connection.XmlConnection;
import com.itests.fx.utility.DOMUtility;

public class PpvOfferValidateTest {

	static ConnectionFactory factory = null;
	static BSDMSessionContext context = null;
	static BSDMSettings settings = null;
	static Connection connection = null;
	static XmlConnection xmlConnection = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		factory = ConnectionFactory.instance();
		settings = BSDMSettings.getDefault();
		context = BSDMSessionContext.getDefaultContext();
		context.setServerId(3);
		connection = factory.createConnection(settings);
		xmlConnection = factory.createXmlConnection(settings, context);
	}

	@Test
	public void ppvOfferValidateDOMTest() throws Exception {
		String xmlFile = "src\\main\\resources\\xml\\responses\\nonUdt\\extendedData\\Ppv\\PpvOfferValidate.xml";
		Document callDOM = DOMUtility.xmlFileToDocument(xmlFile);
		Document responseDOM = xmlConnection.call(callDOM);
		Assert.assertNotNull(responseDOM);
		DOMUtility.printDOM(responseDOM, "Response");
		Node ppvAccountObject = DOMUtility.getNodeByName(responseDOM, "PpvAccountObject");
		Assert.assertEquals("AccountInternalId", ppvAccountObject.getChildNodes().item(0).getNodeName());
		Assert.assertNotNull(ppvAccountObject);
	}

	@Test
	public void ppvOfferValidateDOMUdtTest() throws Exception {
		String xmlFile = "src\\main\\resources\\xml\\responses\\nonUdt\\extendedData\\Ppv\\PpvOfferValidate.xml";
		Document callDOM = DOMUtility.xmlFileToDocument(xmlFile);
		Document responseDOM = xmlConnection.call(callDOM);
		Assert.assertNotNull(responseDOM);
		DOMUtility.printDOM(responseDOM, "Response");
		Node ppvServiceObject = DOMUtility.getNodeByName(responseDOM, "PpvService");
		Assert.assertEquals("Key", ppvServiceObject.getChildNodes().item(5).getNodeName());
		Assert.assertNotNull(ppvServiceObject);
	}

}
