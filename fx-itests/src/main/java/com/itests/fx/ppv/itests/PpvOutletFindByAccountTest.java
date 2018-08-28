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

public class PpvOutletFindByAccountTest {

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
	public void validatePpvOutletFindByAccountDOMTest() throws Exception {
		String xmlFile = "src\\main\\resources\\xml\\responses\\nonUdt\\extendedData\\Ppv\\PpvOutletFindByAccount.xml";
		Document callDOM = DOMUtility.xmlFileToDocument(xmlFile);
		Document responseDOM = xmlConnection.call(callDOM);
		Assert.assertNotNull(responseDOM);
		DOMUtility.printDOM(responseDOM, "Response");
		Node ppvAccountObject = DOMUtility.getNodeByName(responseDOM, "PpvAccountObject");
		Assert.assertNotNull(ppvAccountObject);
	}

	@Test
	public void validatePpvOutletFindByPpvOutletListTest() throws Exception {
		String xmlFile = "src\\main\\resources\\xml\\responses\\nonUdt\\extendedData\\Ppv\\PpvOutletFindByAccount.xml";
		Document callDOM = DOMUtility.xmlFileToDocument(xmlFile);
		Document responseDOM = xmlConnection.call(callDOM);
		Assert.assertNotNull(responseDOM);
		DOMUtility.printDOM(responseDOM, "Response");
		Node ppvOutletList = DOMUtility.getNodeByName(responseDOM, "PpvOutletList");
		Assert.assertNotNull(ppvOutletList);
		Assert.assertEquals("PpvOutlet", ppvOutletList.getChildNodes().item(0).getNodeName());
	}

	@Test
	public void validatePpvOutletFindByPpvOutletTest() throws Exception {
		String xmlFile = "src\\main\\resources\\xml\\responses\\nonUdt\\extendedData\\Ppv\\PpvOutletFindByAccount.xml";
		Document callDOM = DOMUtility.xmlFileToDocument(xmlFile);
		Document responseDOM = xmlConnection.call(callDOM);
		Assert.assertNotNull(responseDOM);
		DOMUtility.printDOM(responseDOM, "Response");
		Node ppvOutlet = DOMUtility.getNodeByName(responseDOM, "PpvOutlet");
		Assert.assertNotNull(ppvOutlet);
		Assert.assertEquals("AccountId", ppvOutlet.getChildNodes().item(0).getNodeName());
		Assert.assertEquals("AddressId", ppvOutlet.getChildNodes().item(1).getNodeName());
		Assert.assertEquals("AdultBit", ppvOutlet.getChildNodes().item(2).getNodeName());
	}

	@Test
	public void ppvOutletFindByAccountDOMUdtTest() throws Exception {
		String xmlFile = "src\\main\\resources\\xml\\responses\\nonUdt\\extendedData\\Ppv\\PpvOutletFindByAccount.xml";
		Document callDOM = DOMUtility.xmlFileToDocument(xmlFile);
		Document responseDOM = xmlConnection.call(callDOM);
		Assert.assertNotNull(responseDOM);
		DOMUtility.printDOM(responseDOM, "Response");
		Node ppvOutletList = DOMUtility.getNodeByName(responseDOM, "PpvOutletList");
		Assert.assertNotNull(ppvOutletList);
	}

}
