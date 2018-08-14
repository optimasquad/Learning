/**
 * 
 */
package com.bfm.analyzer;

/**
 * @author jatin
 *
 */
public class Criteria {

	private String tableName;

	private String columnName;

	private Long extractionTimeout;

	private boolean isReplace;

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * @return the extractionTimeout
	 */
	public Long getExtractionTimeout() {
		return extractionTimeout;
	}

	/**
	 * @param extractionTimeout the extractionTimeout to set
	 */
	public void setExtractionTimeout(Long extractionTimeout) {
		this.extractionTimeout = extractionTimeout;
	}

	/**
	 * @return the isReplace
	 */
	public boolean isReplace() {
		return isReplace;
	}

	/**
	 * @param isReplace the isReplace to set
	 */
	public void setReplace(boolean isReplace) {
		this.isReplace = isReplace;
	}

}
