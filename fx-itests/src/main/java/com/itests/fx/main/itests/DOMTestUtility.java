package com.itests.fx.main.itests;

import java.io.File;

import org.junit.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.itests.fx.utility.DOMUtility;

public class DOMTestUtility {

	public static void validateActualWithExpectedResponses(Document actualReponseDOM, String expectedResponseXmlFile) throws Exception {
		Document expectedResponseDOM = DOMUtility.xmlFileToDocument(expectedResponseXmlFile);
		
		String actualResponse = DOMUtility.convertDocumentToString(actualReponseDOM);
		String expectedResponse = DOMUtility.convertDocumentToString(expectedResponseDOM);
		expectedResponse = expectedResponse.replaceAll(" ", "");
		expectedResponse = expectedResponse.replaceAll("	", "");
		actualResponse = actualResponse.replaceAll(" ", "");
		actualResponse = actualResponse.replaceAll("	", "");
		
		Assert.assertEquals(expectedResponse, actualResponse);
	}
	
	public static void validateDOM(String xsdFilePath, Document dom) {
		try {
			File xsdFile = new File(xsdFilePath);
			DOMUtility.validateDOM(xsdFile, dom);
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public static void validateExtendedData(NodeList extDataList) {
		
	}
	

	public static Node validateResponseStructure(Document responseDOM, String responseName, String topLevelEntityName, String expectedRequestId) {
		Node response = DOMUtility.getNodeByName(responseDOM, responseName);
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getChildNodes());
		Assert.assertNotEquals(0, response.getChildNodes().getLength());
		
		if (responseName.equals("UDTResponse")) {
			response = response.getChildNodes().item(0).getChildNodes().item(0);
			
			// RequestId
			if (expectedRequestId != null) {
				Node requestId = DOMUtility.getNodeByName(responseDOM, "RequestId");
				Assert.assertNotNull(requestId);
				Assert.assertEquals(expectedRequestId, requestId.getChildNodes().item(0).getNodeValue());
				Assert.assertEquals("string", requestId.getAttributes().item(0).getNodeValue());
				
				Node requestObjName = DOMUtility.getNodeByName(responseDOM, "RequestObjName");
				Assert.assertNotNull(requestObjName);
				Assert.assertNotNull(requestObjName.getChildNodes().item(0).getNodeValue());
				Assert.assertEquals("string", requestObjName.getAttributes().item(0).getNodeValue());
			}
		}
		
		Node topLevelEntity = null;
		for (int i=0; i<response.getChildNodes().getLength(); i++) {
			topLevelEntity = response.getChildNodes().item(i);
			Assert.assertNotNull(topLevelEntity);
			Assert.assertEquals(topLevelEntityName, topLevelEntity.getNodeName());
			
			if (topLevelEntityName.endsWith("List")) {
				String entityName = topLevelEntityName.substring(0, topLevelEntityName.indexOf("List"));
				
				// Loop nodes in the response (should include Index, Count and TotalCount as well as the List node)
				for (int j=0; j<topLevelEntity.getChildNodes().getLength(); j++) {
					Node element = topLevelEntity.getChildNodes().item(j);
					Assert.assertNotNull(element);
					if (element.getNodeName().equals(entityName)) {
						return element;
					}
				}
				return topLevelEntity;
			}
		}
		
		if (topLevelEntityName.endsWith("List")) {
			// List node not found
			Assert.fail();
		}
		
		return topLevelEntity;
	}
	
	/**
	 * Validates that the response has an Index, Count, TotalCount. Also the RequestId if this is in UDT the request
	 * @param responseDOM
	 * @param expectedRequestId
	 * @param expectedTotalCount
	 * @param expectedCount
	 * @param expectedIndex
	 */
	public static void validateList(Document responseDOM, Integer expectedTotalCount, Integer expectedCount, Integer expectedIndex) {
		if (expectedIndex == null) {
			expectedIndex = 0;
		}
		
		// Index  (NB. we might have index in the header as well)
		
		NodeList indexes = DOMUtility.getNodesByName(responseDOM, "Index");
		Assert.assertNotNull(indexes);
		for (int i=0; i<indexes.getLength(); i++) {
			Node index = indexes.item(i);
			Assert.assertEquals(""+expectedIndex, index.getChildNodes().item(0).getNodeValue());
			Assert.assertEquals("int", index.getAttributes().item(0).getNodeValue());
		}
		
		// Count
		
		Node count = DOMUtility.getNodeByName(responseDOM, "Count");
		Assert.assertNotNull(count);
		if (expectedCount != null) {
			Assert.assertEquals(""+expectedCount, count.getChildNodes().item(0).getNodeValue());
		}
		else {
			Assert.assertNotNull(count.getChildNodes().item(0).getNodeValue());
		}
		Assert.assertNotNull(count.getChildNodes().item(0).getNodeValue());
		Assert.assertEquals("int", count.getAttributes().item(0).getNodeValue());
		
		// TotalCount
		
		Node totalCount = DOMUtility.getNodeByName(responseDOM, "TotalCount");
		Assert.assertNotNull(totalCount);
		if (expectedTotalCount != null) {
			Assert.assertEquals(""+expectedTotalCount, totalCount.getChildNodes().item(0).getNodeValue());
		}
		else {
			Assert.assertNotNull(totalCount.getChildNodes().item(0).getNodeValue());
		}
		Assert.assertEquals("int", totalCount.getAttributes().item(0).getNodeValue());
	}
	
	public static void validateException(Document responseDOM, 
			String classNameStr, String bsdAppNameStr, String fileNameStr, String functionNameStr, 
			String descStr, String errTextStr, String bsdEnStr, String textIdStr, String bsdFormatStr, 
			Boolean hasReplacementList, Boolean hasStackTrace) 
	{
		Node exception = DOMUtility.getNodeByName(responseDOM, "Exception");
		Node className = DOMUtility.getNodeByName(responseDOM, "Class");
		Node description = DOMUtility.getNodeByName(responseDOM, "Description");
		Node file = DOMUtility.getNodeByName(responseDOM, "File");
		Node function = DOMUtility.getNodeByName(responseDOM, "Function");
		Node item = DOMUtility.getNodeByName(responseDOM, "Item");
		Node bsdAppName = DOMUtility.getNodeByName(responseDOM, "BSD_APPNAME");
		Node bsdEn = DOMUtility.getNodeByName(responseDOM, "BSD_EN");
		Node bsdTextId = DOMUtility.getNodeByName(responseDOM, "BSD_TEXTID");
		Node bsdErrorText = DOMUtility.getNodeByName(responseDOM, "BSD_ERRORTEXT");
		Node bsdFormat = DOMUtility.getNodeByName(responseDOM, "BSD_FORMAT");
		Node line = DOMUtility.getNodeByName(responseDOM, "Line");
		Node name = DOMUtility.getNodeByName(responseDOM, "Name");
		Node traceStack = DOMUtility.getNodeByName(responseDOM, "TraceStack");
		
		Assert.assertNotNull(exception);
		Assert.assertNotNull(className);
		if (classNameStr != null) {
			Assert.assertEquals(classNameStr, className.getFirstChild().getNodeValue());
		}
		Assert.assertNotNull(description);
		if (descStr != null) {
			Assert.assertEquals(descStr, description.getFirstChild().getNodeValue());
		}
		Assert.assertNotNull(file);
		if (fileNameStr != null) {
			Assert.assertEquals(fileNameStr, file.getFirstChild().getNodeValue());
		}
		Assert.assertNotNull(function);
		if (functionNameStr != null) {
			Assert.assertEquals(functionNameStr, function.getFirstChild().getNodeValue());
		}
		Assert.assertNotNull(item);
		Assert.assertNotNull(bsdAppName);
		if (bsdAppNameStr != null) {
			Assert.assertEquals(bsdAppNameStr, bsdAppName.getFirstChild().getNodeValue());
		}
		Assert.assertNotNull(bsdEn);
		if (bsdEnStr != null) {
			Assert.assertEquals(bsdEnStr, bsdEn.getFirstChild().getNodeValue());
		}
		Assert.assertNotNull(bsdTextId);
		if (textIdStr != null) {
			Assert.assertEquals(textIdStr, bsdTextId.getFirstChild().getNodeValue());
		}
		Assert.assertNotNull(bsdErrorText);
		if (errTextStr != null) {
			Assert.assertEquals(errTextStr, bsdErrorText.getFirstChild().getNodeValue());
		}
		Assert.assertNotNull(bsdFormat);
		if (bsdFormatStr != null) {
			Assert.assertEquals(bsdFormatStr, bsdFormat.getFirstChild().getNodeValue());
		}
		Assert.assertNotNull(line);
		Assert.assertNotNull(name);
		if (textIdStr != null) {
			Assert.assertEquals(textIdStr, name.getFirstChild().getNodeValue());
		}
		if (hasStackTrace) {
			Assert.assertNotNull(traceStack);
		}
	}
}
