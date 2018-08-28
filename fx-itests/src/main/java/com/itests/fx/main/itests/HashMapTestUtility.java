package com.itests.fx.main.itests;

import java.util.Map;

import org.junit.Assert;

public class HashMapTestUtility {
	public static void validateResponse(Map callResponse, String apiName) {
		Assert.assertNotNull(callResponse);
		Assert.assertNotNull(callResponse.get(apiName));
//        Assert.assertNotNull(callResponse.get("Header"));
//        Assert.assertNotNull(callResponse.get("Request"));
	}
	
	public static void validateField(Map callResponse, String apiName, String objName, String fieldName, Boolean isUdt) {
//		Assert.assertNotNull(callResponse);
//		Map header = (Map) callResponse.get("Header");
//		Assert.assertNotNull(header);
//        Map response = (Map) callResponse.get("Request");
//        Assert.assertNotNull(response);
        
        if (isUdt) {
        	callResponse = (Map) callResponse.get("CustomerUdtRequest");
        	Assert.assertNotNull(callResponse);
        }
        
        Map apiResult = (Map) callResponse.get(apiName);
        Assert.assertNotNull(apiResult);
        Map objResult = (Map) apiResult.get(objName);
        Assert.assertNotNull(objResult);
        Map fieldResult = (Map) objResult.get(fieldName);
        Assert.assertNotNull(fieldResult);
	}
	
	public static void validateList(Map callResponse, String expectedRequestId, Integer expectedTotalCount, Integer expectedCount, Integer expectedIndex) {
		if (expectedIndex == null) {
			expectedIndex = Integer.valueOf(0);
		}
		Assert.assertEquals(expectedIndex, callResponse.get("Index"));
		if (expectedCount != null) {
			Assert.assertEquals(expectedCount, callResponse.get("Count"));
		}
		else {
			Assert.assertNotNull(callResponse.get("Count"));
		}
		if (expectedTotalCount != null) {
			Assert.assertEquals(expectedTotalCount, callResponse.get("TotalCount"));
		}
		else {
			Assert.assertNotNull(callResponse.get("TotalCount"));
		}
	}
}
