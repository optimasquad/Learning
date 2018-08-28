package com.itests.fx.main.itests;

import org.junit.Assert;

import com.csgsystems.aruba.ArubaObjectList;

public class BeanTestUtility {
	public static void validateList(ArubaObjectList list, Integer expectedCount, Integer expectedTotalCount, Integer expectedIndex) {
		Assert.assertNotNull(list);
		
		if (expectedIndex == null) {
			expectedIndex = 0;
		}
		Assert.assertEquals(expectedIndex, Integer.valueOf(list.getIndex()));
		
		if (expectedCount != null) {
			Assert.assertEquals(expectedCount, Integer.valueOf(list.getLength()));
		}
		else {
			Assert.assertNotNull(list.getLength());
		}
		
		if (expectedTotalCount != null) {
			Assert.assertEquals(expectedTotalCount, Integer.valueOf(list.getTotalCount()));
		}
		else {
			Assert.assertNotNull(list.getTotalCount());
		}
	}

}
