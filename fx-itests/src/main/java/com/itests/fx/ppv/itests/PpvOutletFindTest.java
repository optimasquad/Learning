package com.itests.fx.ppv.itests;

import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.csgsystems.aruba.connection.BSDMSessionContext;
import com.csgsystems.aruba.connection.BSDMSettings;
import com.csgsystems.aruba.connection.Connection;
import com.csgsystems.aruba.connection.ConnectionFactory;
import com.csgsystems.aruba.connection.XmlConnection;
import com.csgsystems.bali.connection.ApiMappings;
import com.itests.fx.main.itests.HashMapTestUtility;
import com.itests.fx.utility.DOMUtility;
import com.itests.fx.utility.HashMapUtility;

public class PpvOutletFindTest {
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

	/*@Test
	public void validatePpvOutletFindTotalCountUdtTest() throws Exception {
		IntegerFilter[] accountId = new IntegerFilter[1];
		accountId[0] = new IntegerEquals(1900);

		PpvOutletObjectFilter filter = new PpvOutletObjectFilter();
		filter.setAccountIdFetch(true);
		filter.setAccountIdFilter(accountId);

		PpvOutletRequest ppvOutletRequest = new PpvOutletRequest("BeanTestRequest",
				PpvOutletRequestMethod.PPV_OUTLET_FIND);
		ppvOutletRequest.setPpvOutletForPpvOutletFind(filter);

		CustomerUdtRequest rootRequest = new CustomerUdtRequest();
		CustomerUdtRequestBean processor = new CustomerUdtRequestBean(settings);

		rootRequest.addPpvOutletRequest(ppvOutletRequest);

		rootRequest = processor.process(context, rootRequest);

		PpvOutletObjectDataList ppvOutletList = ppvOutletRequest.getPpvOutletObjectDataPpvOutletFromPpvOutletFind();

		Assert.assertNotNull(ppvOutletList);
		Assert.assertEquals(0, ppvOutletList.getIndex());
		Assert.assertNotNull(ppvOutletList.getTotalCount());
	}
*/
	@Test
	public void validatePpvOutletFindHashMap() throws Exception {
		Map<String, Object> criteria = HashMapUtility.createField("Equal", 12232);
		Map<String, Object> filter = HashMapUtility.createField("AccountId", criteria);

		Map<String, Object> inputs = HashMapUtility.createField("PpvOutlet", filter);

		String callName = ApiMappings.getCallName("PpvOutletFind");
		Map callResponse = connection.call(context, callName, inputs);

		HashMapUtility.printHashMap(callResponse);

		HashMapTestUtility.validateResponse(callResponse, "PpvOutletList");
	}

	@Test
	public void validatePpvOutletFindDOM() throws Exception {
		String xmlFile = "src\\main\\resources\\xml\\responses\\nonUdt\\extendedData\\Ppv\\PpvOutletFind.xml";

		Document callDOM = DOMUtility.xmlFileToDocument(xmlFile);

		Document responseDOM = xmlConnection.call(callDOM);

		Assert.assertNotNull(responseDOM);

		DOMUtility.printDOM(responseDOM, "Response");

		NodeList ppvOutletList = DOMUtility.getNodesByName(responseDOM, "PpvOutletList");
		Assert.assertNotNull(ppvOutletList);
		Assert.assertEquals(1, ppvOutletList.getLength());

	}

	@Test
	public void validatePpvOutletNodeDOMUdt() throws Exception {
		String xmlFile = "src\\main\\resources\\xml\\responses\\nonUdt\\extendedData\\Ppv\\PpvOutletFind.xml";

		Document callDOM = DOMUtility.xmlFileToDocument(xmlFile);

		Document responseDOM = xmlConnection.call(callDOM);

		Assert.assertNotNull(responseDOM);

		DOMUtility.printDOM(responseDOM, "Response");

		NodeList ppvOutletList = DOMUtility.getNodesByName(responseDOM, "PpvOutlet");
		Assert.assertNotNull(ppvOutletList);
		Assert.assertEquals(284, ppvOutletList.getLength());

	}
}
