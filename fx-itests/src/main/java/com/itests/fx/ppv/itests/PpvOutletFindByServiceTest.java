/**
 * 
 */
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

/**
 * @author jatinma
 *
 */
public class PpvOutletFindByServiceTest {

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
	public void ppvOutletFindByServiceDOMTest() throws Exception {
		String xmlFile = "src\\main\\resources\\xml\\responses\\nonUdt\\extendedData\\Ppv\\PpvOutletFindByService.xml";
		Document callDOM = DOMUtility.xmlFileToDocument(xmlFile);
		Document responseDOM = xmlConnection.call(callDOM);
		DOMUtility.printDOM(responseDOM, "Response");
		Node ppvAccountObject = DOMUtility.getNodeByName(responseDOM, "PpvAccountObject");
		Assert.assertEquals("NoOutletFlag",ppvAccountObject.getChildNodes().item(4).getNodeName());
		Assert.assertNotNull(responseDOM);
		Assert.assertNotNull(ppvAccountObject);
	}

}
