package com.bfm.analyzer;

/**
 * 
 */


/**
 * @author jatin
 *
 */
public class AnalyzerDto {

	private String deepSearchRequired;

	private String extractionTimeout;

	private String searchPattern;

	/**
	 * @return the deepSearchRequired
	 */
	public String getDeepSearchRequired() {
		return deepSearchRequired;
	}

	/**
	 * @param deepSearchRequired
	 *            the deepSearchRequired to set
	 */
	public void setDeepSearchRequired(String deepSearchRequired) {
		this.deepSearchRequired = deepSearchRequired;
	}

	/**
	 * @return the extractionTimeout
	 */
	public String getExtractionTimeout() {
		return extractionTimeout;
	}

	/**
	 * @param extractionTimeout
	 *            the extractionTimeout to set
	 */
	public void setExtractionTimeout(String extractionTimeout) {
		this.extractionTimeout = extractionTimeout;
	}

	/**
	 * @return the searchPattern
	 */
	public String getSearchPattern() {
		return searchPattern;
	}

	/**
	 * @param searchPattern
	 *            the searchPattern to set
	 */
	public void setSearchPattern(String searchPattern) {
		this.searchPattern = searchPattern;
	}

}
