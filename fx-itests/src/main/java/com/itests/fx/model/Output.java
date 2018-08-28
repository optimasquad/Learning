package com.itests.fx.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Output implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1110339307111576556L;

	@JsonProperty("Account")
	private Account Account;

	@JsonProperty("AccountServerId")
	private int AccountServerId;

	@JsonProperty("RequestId")
	private String RequestId;

	@JsonProperty("RequestObjName")
	private String RequestObjName;

	/**
	 * @return the account
	 */
	public Account getAccount() {
		return Account;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(Account account) {
		Account = account;
	}

	/**
	 * @return the accountServerId
	 */
	public int getAccountServerId() {
		return AccountServerId;
	}

	/**
	 * @param accountServerId
	 *            the accountServerId to set
	 */
	public void setAccountServerId(int accountServerId) {
		AccountServerId = accountServerId;
	}

	/**
	 * @return the requestId
	 */
	public String getRequestId() {
		return RequestId;
	}

	/**
	 * @param requestId
	 *            the requestId to set
	 */
	public void setRequestId(String requestId) {
		RequestId = requestId;
	}

	/**
	 * @return the requestObjName
	 */
	public String getRequestObjName() {
		return RequestObjName;
	}

	/**
	 * @param requestObjName
	 *            the requestObjName to set
	 */
	public void setRequestObjName(String requestObjName) {
		RequestObjName = requestObjName;
	}

}
