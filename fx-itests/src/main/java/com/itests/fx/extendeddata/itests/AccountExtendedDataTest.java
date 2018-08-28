package com.itests.fx.extendeddata.itests;

import static org.junit.Assert.fail;

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
import com.csgsystems.aruba.connection.ServiceException;
import com.csgsystems.aruba.connection.XmlConnection;
import com.itests.fx.utility.DOMUtility;

public class AccountExtendedDataTest {
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

	// Get

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

	@Test
	public void accountGetWithExtendedDataDOMUdtTest() throws Exception {
		String xmlFile = "xml\\requests\\udt\\positiveTests\\extendedData\\AccountGet.xml";

		Document callDOM = DOMUtility.xmlFileToDocument(xmlFile);
		// DOMUtility.printDOM(callDOM, "Request");
		// DOMUtility.validateDOM(xsdFile, callDOM);

		Document responseDOM = xmlConnection.call(callDOM);

		Assert.assertNotNull(responseDOM);

		DOMUtility.printDOM(responseDOM, "Response");

		NodeList extDataParams = DOMUtility.getNodesByName(responseDOM, "ExtendedDataParam");
		Assert.assertNotNull(extDataParams);
		Assert.assertEquals(16, extDataParams.getLength());
		Assert.assertNotNull(extDataParams.item(0).getChildNodes());
		Assert.assertEquals(3, extDataParams.item(0).getChildNodes().getLength());
		Assert.assertEquals("ParamId", extDataParams.item(0).getChildNodes().item(0).getNodeName());
		Assert.assertEquals("ParamName", extDataParams.item(0).getChildNodes().item(1).getNodeName());
		Assert.assertEquals("ParamValue", extDataParams.item(0).getChildNodes().item(2).getNodeName());
	}

	// Find

	@Test
	public void accountExtendedDataFindForExistingAccountBeanTest() throws Exception {
		fail("Not yet implemented");
	}

	@Test(expected = ServiceException.class)
	public void accountExtendedDataFindForNonExistingAccountBeanTest() throws Exception {
		fail("Not yet implemented");
	}

	@Test
	public void accountExtendedDataFindForExistingAccountHashMapTest() throws Exception {
		fail("Not yet implemented");
	}

	@Test(expected = ServiceException.class)
	public void accountExtendedDataFindForNonExistingAccountHashMapTest() throws Exception {
		fail("Not yet implemented");
	}

	@Test
	public void accountExtendedDataFindForExistingAccountDOMTest() throws Exception {
		fail("Not yet implemented");
	}

	@Test(expected = ServiceException.class)
	public void accountExtendedDataFindForNonExistingAccountDOMTest() throws Exception {
		fail("Not yet implemented");
	}

	// Update

	@Test
	public void accountExtendedDataUpdateForExistingAccountBeanTest() throws Exception {
		fail("Not yet implemented");
	}

	@Test(expected = ServiceException.class)
	public void accountExtendedDataUpdateForNonExistingAccountBeanTest() throws Exception {
		fail("Not yet implemented");
	}

	@Test
	public void accountExtendedDataUpdateForExistingAccountHashMapTest() throws Exception {
		fail("Not yet implemented");
	}

	@Test(expected = ServiceException.class)
	public void accountExtendedDataUpdateForNonExistingAccountHashMapTest() throws Exception {
		fail("Not yet implemented");
	}

	@Test
	public void accountExtendedDataUpdateForExistingAccountDOMTest() throws Exception {
		String xmlFile = "xml\\requests\\nonUdt\\positiveTests\\extendedData\\AccountUpdateExtendedData.xml";

		Document callDOM = DOMUtility.xmlFileToDocument(xmlFile);
		// DOMUtility.printDOM(callDOM, "Request");
		// DOMUtility.validateDOM(xsdFile, callDOM);

		Document responseDOM = xmlConnection.call(callDOM);

		Assert.assertNotNull(responseDOM);

		DOMUtility.printDOM(responseDOM, "Response");

		NodeList extDataParams = DOMUtility.getNodesByName(responseDOM, "ExtendedDataParam");
		Assert.assertNotNull(extDataParams);
		Assert.assertEquals(16, extDataParams.getLength());

		NodeList extDataAttributes = extDataParams.item(0).getChildNodes();
		Assert.assertNotNull(extDataAttributes);
		Assert.assertEquals(3, extDataAttributes.getLength());
		Assert.assertEquals("ParamId", extDataAttributes.item(0).getNodeName());
		Assert.assertEquals("ParamName", extDataAttributes.item(1).getNodeName());
		Assert.assertEquals("ParamValue", extDataAttributes.item(2).getNodeName());

		xmlFile = "xml\\requests\\nonUdt\\positiveTests\\extendedData\\AccountUpdateExtendedDataRestore.xml";

		callDOM = DOMUtility.xmlFileToDocument(xmlFile);
		// DOMUtility.printDOM(callDOM, "Request");
		// DOMUtility.validateDOM(xsdFile, callDOM);

		responseDOM = xmlConnection.call(callDOM);

		Assert.assertNotNull(responseDOM);

		DOMUtility.printDOM(responseDOM, "Response");

		extDataParams = DOMUtility.getNodesByName(responseDOM, "ExtendedDataParam");
		Assert.assertNotNull(extDataParams);
		Assert.assertEquals(16, extDataParams.getLength());

		extDataAttributes = extDataParams.item(0).getChildNodes();
		Assert.assertNotNull(extDataAttributes);
		Assert.assertEquals(3, extDataAttributes.getLength());
		Assert.assertEquals("ParamId", extDataAttributes.item(0).getNodeName());
		Assert.assertEquals("ParamName", extDataAttributes.item(1).getNodeName());
		Assert.assertEquals("ParamValue", extDataAttributes.item(2).getNodeName());
	}

	@Test
	public void accountExtendedDataUpdateForExistingAccountDOMUdtTest() throws Exception {
		String xmlFile = "xml\\requests\\udt\\positiveTests\\extendedData\\AccountUpdateExtendedData.xml";

		Document callDOM = DOMUtility.xmlFileToDocument(xmlFile);
		// DOMUtility.printDOM(callDOM, "Request");
		// DOMUtility.validateDOM(xsdFile, callDOM);

		Document responseDOM = xmlConnection.call(callDOM);

		Assert.assertNotNull(responseDOM);

		DOMUtility.printDOM(responseDOM, "Response");

		NodeList extDataParams = DOMUtility.getNodesByName(responseDOM, "ExtendedDataParam");
		Assert.assertNotNull(extDataParams);
		Assert.assertEquals(16, extDataParams.getLength());

		NodeList extDataAttributes = extDataParams.item(0).getChildNodes();
		Assert.assertNotNull(extDataAttributes);
		Assert.assertEquals(3, extDataAttributes.getLength());
		Assert.assertEquals("ParamId", extDataAttributes.item(0).getNodeName());
		Assert.assertEquals("ParamName", extDataAttributes.item(1).getNodeName());
		Assert.assertEquals("ParamValue", extDataAttributes.item(2).getNodeName());

		xmlFile = "xml\\requests\\udt\\positiveTests\\extendedData\\AccountUpdateExtendedDataRestore.xml";

		callDOM = DOMUtility.xmlFileToDocument(xmlFile);
		// DOMUtility.printDOM(callDOM, "Request");
		// DOMUtility.validateDOM(xsdFile, callDOM);

		responseDOM = xmlConnection.call(callDOM);

		Assert.assertNotNull(responseDOM);

		DOMUtility.printDOM(responseDOM, "Response");

		extDataParams = DOMUtility.getNodesByName(responseDOM, "ExtendedDataParam");
		Assert.assertNotNull(extDataParams);
		Assert.assertEquals(16, extDataParams.getLength());

		extDataAttributes = extDataParams.item(0).getChildNodes();
		Assert.assertNotNull(extDataAttributes);
		Assert.assertEquals(3, extDataAttributes.getLength());
		Assert.assertEquals("ParamId", extDataAttributes.item(0).getNodeName());
		Assert.assertEquals("ParamName", extDataAttributes.item(1).getNodeName());
		Assert.assertEquals("ParamValue", extDataAttributes.item(2).getNodeName());
	}

	@Test(expected = ServiceException.class)
	public void accountExtendedDataUpdateForNonExistingAccountDOMTest() throws Exception {
		fail("Not yet implemented");
	}

	// Create / Delete

	@Test(expected = ServiceException.class)
	public void accountExtendedDataCreateInvalidBeanTest() throws Exception {
		fail("Not yet implemented");
	}

	@Test(expected = ServiceException.class)
	public void accountExtendedDataCreateDuplicateBeanTest() throws Exception {
		fail("Not yet implemented");
	}

	@Test
	public void accountOptionalExtendedDataCreateAndDeleteForExistingAccountBeanTest() throws Exception {
		fail("Not yet implemented");
	}

	@Test(expected = ServiceException.class)
	public void accountMandatoryExtendedDataDeleteForExistingAccountBeanTest() throws Exception {
		fail("Not yet implemented");
	}

	@Test(expected = ServiceException.class)
	public void accountExtendedDataDeleteForNonExistingAccountBeanTest() throws Exception {
		fail("Not yet implemented");
	}

	@Test(expected = ServiceException.class)
	public void accountExtendedDataCreateInvalidHashMapTest() throws Exception {
		fail("Not yet implemented");
	}

	@Test(expected = ServiceException.class)
	public void accountExtendedDataCreateDuplicateHashMapTest() throws Exception {
		fail("Not yet implemented");
	}

	@Test
	public void accountOptionalExtendedDataCreateAndDeleteForExistingAccountHashMapTest() throws Exception {
		fail("Not yet implemented");
	}

	@Test
	public void accountMandatoryExtendedDataDeleteForExistingAccountHashMapTest() throws Exception {
		fail("Not yet implemented");
	}

	@Test(expected = ServiceException.class)
	public void accountExtendedDataDeleteForNonExistingAccountHashMapTest() throws Exception {
		fail("Not yet implemented");
	}

	@Test(expected = ServiceException.class)
	public void accountExtendedDataCreateInvalidDOMTest() throws Exception {
		fail("Not yet implemented");
	}

	@Test(expected = ServiceException.class)
	public void accountExtendedDataCreateDuplicateDOMTest() throws Exception {
		fail("Not yet implemented");
	}

	@Test
	public void accountOptionalExtendedDataCreateAndDeleteForExistingAccountDOMTest() throws Exception {
		fail("Not yet implemented");
	}

	@Test
	public void accountMandatoryExtendedDataDeleteForExistingAccountDOMTest() throws Exception {
		fail("Not yet implemented");
	}

	@Test
	public void accountExtendedDataDeleteForNonExistingAccountDOMTest() throws Exception {
		fail("Not yet implemented");
	}

}
