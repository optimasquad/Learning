package com.itests.fx.extendeddata.itests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.amdocs.api.framework.client.UserContext;
import com.csgsystems.aruba.connection.BSDMSessionContext;
import com.csgsystems.aruba.connection.BSDMSettings;
import com.csgsystems.aruba.connection.Connection;
import com.csgsystems.aruba.connection.ConnectionFactory;
import com.csgsystems.aruba.connection.XmlConnection;
import com.itests.fx.utility.DOMUtility;

public class AccountGetTest {

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

	@Before
	public void setUp() throws Exception {
		context.setServerId(3);
		context.setTenantId(2);
		context.setOperatorName("arborsv");
	}

	@After
	public void tearDown() throws Exception {
		UserContext.resetInstance();
	}

	@Test
	public void accountGetWithExtendedDataDOMTest() throws Exception {
		String xmlFile = "xml\\requests\\nonUdt\\positiveTests\\extendedData\\AccountGet.xml";
		Document callDOM = DOMUtility.xmlFileToDocument(xmlFile);
		Document responseDOM = xmlConnection.call(callDOM);
		Assert.assertNotNull(responseDOM);
		DOMUtility.printDOM(responseDOM, "Response");
		NodeList extDataParams = DOMUtility.getNodesByName(responseDOM, "ExtendedDataParam");
		Assert.assertNotNull(extDataParams);
	}

}
