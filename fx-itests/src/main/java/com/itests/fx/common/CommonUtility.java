/**
 * 
 */
package com.itests.fx.common;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Date;

import org.springframework.http.HttpHeaders;

/**
 * @author jatinma
 * 
 *         This is the CommonUtility used for the classed to perform the common
 *         functions or we say as the generic functions
 *
 */
public class CommonUtility {

	/**
	 * 
	 * 
	 * @param dirPath
	 *            :String
	 * @return
	 */
	public static File[] getFileList(String dirPath,String fileExtension) {
		File dir = new File(dirPath);
		File[] fileList = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith("."+fileExtension);
			}
		});
		return fileList;
	}

	public static HttpHeaders mapHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", PropertyLoader.getPropValue("Accept"));
		headers.add("tenantId", PropertyLoader.getPropValue("tenantId"));
		headers.add("operatorName", PropertyLoader.getPropValue("operatorName"));
		headers.set("Authorization", PropertyLoader.getPropValue("Authorization"));
		return headers;

	}

	public static Date getDate() {
		return new Date();
	}

}
