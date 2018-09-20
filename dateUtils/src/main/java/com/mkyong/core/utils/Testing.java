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

	private static boolean updateColTagFlag = false;

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
		ComplDef.setSecJql("test1_udf$fxvdbbrrnr*fvbdbb");
		listOfComplDef.add(ComplDef);
		complDefMap.put("test1_", listOfComplDef);

		// replacement to be done

		// Key is the pcolDef tags
		// value is basically the list containing whole of the jql formed

		for (Map.Entry<String, List<ComplDef>> mm : complDefMap.entrySet()) {

			for (ComplDef complDef : mm.getValue()) {
				String secJql = containsSecJql(mm.getValue(), mm.getKey());
				if (updateColTagFlag) {
					System.out.println("Updated Col Tag");

					// you need to store the updated coltag and the values and format the final
					// List....So we can combine whole of the
					// data now
					// we have the BQL Scan Response and then we can formaulate the BQL Replace
					// Response for the final
					// BQL Consist of the Col_Tag as the Key +program+BQL Function ..If the Col_tag
					// is comma Separated could be printed
					// after retrieving the value from the Key easily then ...Otherwise would'nt be
					// possible though

				} else {

					System.out.println("Remians the same ");
				}
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
		updateColTagFlag = false;
		if (list.stream().anyMatch(ti -> ti.getSecJql().contains(secJql))) {
			// contains the id
			updateColTagFlag = true;
			return list.stream().filter(ti -> ti.getSecJql().contains(secJql)).findFirst().get().getNewSecJql();
		}
		return secJql;
	}

}
