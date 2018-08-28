package com.itests.fx.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jatinma
 *
 */
public class JsonConfigFileReaderUtil {

	private static Logger log = LoggerFactory.getLogger(JsonConfigFileReaderUtil.class);

	/**
	 * 
	 * @param fileName
	 * @return JSONObject
	 */
	public static String loadRequestFromStream(String fileName) {
		InputStream inputStream = null;
		String jsonTxt = "";
		try {
			inputStream = JsonConfigFileReaderUtil.class.getResource("/config/" + fileName) != null
					? JsonConfigFileReaderUtil.class.getResourceAsStream("/config/" + fileName) : null;
			if (inputStream != null) {
				BufferedReader configData = new BufferedReader(new InputStreamReader(inputStream));

				String line = "";
				while ((line = configData.readLine()) != null) {
					jsonTxt += line;
				}
			}
		} catch (IOException e) {
			log.error("Error Loading Config file {} :", fileName, e);
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				// This is unrecoverable. Just report it and move on
				e.printStackTrace();
			}
		}
		return jsonTxt;
	}
}
