package com.itests.fx.common;

public enum ItestsEnum {

	AccountInternalId("AccountInternalId"), ServiceInternalId("ServiceInternalId"), ServiceInternalIdResets(
			"ServiceInternalIdResets"), UNKNOWN("");

	private String value;

	ItestsEnum(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}