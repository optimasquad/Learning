/**
 * 
 */
package com.itests.fx.common;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author jatinma
 *
 */
public class PropertyLoader {

	private static Properties prop = new Properties();

	public static String getPropValue(final String propKey) {

		if (PropertyLoader.prop == null) {

			throw new IllegalStateException("No props loaded.Please call loadPropValues first");

		}
		return PropertyLoader.prop.getProperty(propKey, null);
	}

	public static void loadPropValues() {
		try {
			PropertyLoader.prop = new Properties();
			final String propFileName = "config.properties";
			final InputStream inputStream = PropertyLoader.class.getClassLoader().getResourceAsStream(propFileName);

			if (null != inputStream) {
				PropertyLoader.prop.load(inputStream);
			} else {
				throw new FileNotFoundException("Property File" + propFileName + " not found in the classPath");
			}

		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}

}
