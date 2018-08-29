package com.bfm.analyzer;

import java.io.Serializable;

public class EnumerationData implements Serializable {
	private static final long serialVersionUID = 4060279536514105405L;

	private String key;

	private String displayValue;

	public EnumerationData(String key) {
		this.key = key;
	}

	public EnumerationData() {
	}

	public static EnumerationData enumerationDataFactory(Object key) {
		return key != null ? new EnumerationData(key.toString()) : null;
	}

	public boolean equals(Object object) {
		if ((object instanceof EnumerationData)) {
			EnumerationData enumerationData = (EnumerationData) object;
			return (key.equals(key)) && (displayValue.equals(displayValue));
		}
		return false;
	}

	public int hashCode() {
		int result = 17;
		result = 31 * result + (key != null ? key.hashCode() : 0);
		result = 31 * result + (displayValue != null ? displayValue.hashCode() : 0);
		return result;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
}
