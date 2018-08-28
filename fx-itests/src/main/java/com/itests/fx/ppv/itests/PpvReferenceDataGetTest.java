package com.itests.fx.ppv.itests;

import java.util.HashMap;
import java.util.Map;

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
import com.csgsystems.bali.connection.ApiMappings;
import com.itests.fx.utility.DOMUtility;
import com.itests.fx.utility.HashMapUtility;

public class PpvReferenceDataGetTest {

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
		context.setTenantId(2);
		context.setOperatorName("tarborsv");
		connection = factory.createConnection(settings);
		xmlConnection = factory.createXmlConnection(settings, context);
	}

	@Test
	public void ppvReferenceDataGetHashMapTest() throws Exception {
		String callName = ApiMappings.getCallName("PpvReferenceDataGet");
		Map referenceData = new HashMap();
		Map callResponse = connection.call(context, callName, referenceData);
		HashMapUtility.printHashMap(callResponse);
		Assert.assertNotNull(callResponse);
		Assert.assertNotNull(callResponse.get("DateTime"));
	}

	/**
	 * An unusual one with no inputs at all
	 * 
	 * @throws Exception
	 */
	@Test
	public void validatePpvReferenceDataGetDOMTest() throws Exception {
		String xmlFile = "src\\main\\resources\\xml\\responses\\nonUdt\\extendedData\\Ppv\\PpvReferenceDataGet.xml";
		Document callDOM = DOMUtility.xmlFileToDocument(xmlFile);
		Document responseDOM = xmlConnection.call(callDOM);
		Assert.assertNotNull(responseDOM);
		DOMUtility.printDOM(responseDOM, "Response");
		Node ppvReferenceData = DOMUtility.getNodeByName(responseDOM, "PpvReferenceData");
		Assert.assertNotNull(ppvReferenceData);
		Node dateTime = ppvReferenceData.getChildNodes().item(0);
		Assert.assertNotNull(dateTime);
		System.out.println(dateTime.getAttributes().item(0).getNodeValue());
		Assert.assertEquals("dateTime", dateTime.getAttributes().item(0).getNodeValue());
	}

	@Test
	public void validatePpvReferenceDataGetDOMUDTTest() throws Exception {
		String xmlFile = "src\\main\\resources\\xml\\responses\\nonUdt\\extendedData\\Ppv\\PpvReferenceDataGet.xml";
		Document callDOM = DOMUtility.xmlFileToDocument(xmlFile);
		Document responseDOM = xmlConnection.call(callDOM);
		Assert.assertNotNull(responseDOM);
		DOMUtility.printDOM(responseDOM, "Response");
		Node dateTime = DOMUtility.getNodeByName(responseDOM, "DateTime");
		Assert.assertNotNull(dateTime);
		Assert.assertEquals("dateTime", dateTime.getAttributes().item(0).getNodeValue());
	}

}
