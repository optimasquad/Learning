package extractor.impl.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author jatin
 *
 */
public class PropertyLoader {

	private static Properties prop = null;

	public static String getPropValue(final String propKey) {

		if (PropertyLoader.prop == null) {

			throw new IllegalStateException("No props loaded.Please call loadPropValues first");

		}
		return PropertyLoader.prop.getProperty(propKey, null);
	}

	public static void loadPropValues() throws IOException {
		if (PropertyLoader.prop == null) {
			PropertyLoader.prop = new Properties();
			final String propFileName = "config.properties";

			final InputStream inputStream = PropertyLoader.class.getClassLoader().getResourceAsStream(propFileName);

			if (null != inputStream) {
				PropertyLoader.prop.load(inputStream);
			} else {
				throw new FileNotFoundException("Property File" + propFileName + " not found in the classPath");
			}

		}
	}

	public static void main(String args[]) {
		try {
			PropertyLoader.loadPropValues();
		} catch (final IOException io) {
			io.printStackTrace();
		}

	}
}
