/**
 * 
 */
package com.mkyong.core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hp
 *
 */
public class Testing {

	public static void main(String args[]) {

		List<PcolDef> pcolDefList = new ArrayList<>();
		PcolDef PcolDef = new PcolDef();
		PcolDef.setColtag("test1");
		pcolDefList.add(PcolDef);

		// These List are full now
		Map<String, List<ComplDef>> complDefMap = new HashMap<>();
		List<ComplDef> listOfComplDef = new ArrayList<>();
		ComplDef ComplDef = new ComplDef();
		ComplDef.setNewSecJql("test1ibhbbtesti12");
		ComplDef.setSecJql("testing");
		listOfComplDef.add(ComplDef);
		complDefMap.put("test1_udf", listOfComplDef);

		// replacement to be done

		for (Map.Entry<String, List<ComplDef>> mm : complDefMap.entrySet()) {

			for (ComplDef complDef : mm.getValue()) {
				String response = containsSecJql(mm.getValue(), complDef.getSecJql());

				// Do the manupulation of response

			}

		}

		// update operation is performed..chck if the secjql again exists or not..If it
		// exists then return yes..Otherwise return the secjql

		// These List are full now
		Map<String, List<TeComplDef>> teComplDef = new HashMap<>();

		// All are filled

	}

	public static String containsSecJql(final List<ComplDef> list, final String secJql) {

		if (list.stream().anyMatch(ti -> ti.getSecJql() == secJql)) {
			// contains the id
			return list.stream().filter(ti -> ti.getSecJql() == secJql).findFirst().get().getNewSecJql();
		}
		return secJql;
	}

}
