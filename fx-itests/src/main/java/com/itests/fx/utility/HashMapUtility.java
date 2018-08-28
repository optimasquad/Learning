package com.itests.fx.utility;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.csgsystems.aruba.connection.ServiceException;

public class HashMapUtility {

	public static Map createRequest(String apiName) {
		Map<String, Object> request = new LinkedHashMap<String, Object>();
		return request;
	}

	public static Map<String, Object> createField(String fieldName, Object value) {
		Map<String, Object> field = new HashMap<String, Object>();
		field.put(fieldName, value);
		return field;
	}

	public static Map<String, Object> createFilterObject(String filterObjName, Map<String, Object> filterObj,
			Boolean fetch, Integer index) {
		if (fetch) {
			filterObj.put("Fetch", new Boolean(true));
		}
		if (index != null) {
			filterObj.put("Index", index);
		}

		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put(filterObjName, filterObj);

		return filter;
	}

	/**
	 * Utility method for printing HashMaps.
	 * 
	 * @param map
	 *            HashMap to be printed.
	 */
	public static void printHashMap(Map map) {
		try {
			// Create a BufferedWriter
			BufferedWriter bw = new BufferedWriter(new PrintWriter(System.out));

			// Pass the map to the ServiceException object's print() method
			ServiceException.printMap(bw, map, 2);

			// Flush the BufferedWriter
			bw.flush();
		}

		// Handle input/output exceptions
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
