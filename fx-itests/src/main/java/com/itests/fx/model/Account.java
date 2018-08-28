/**
 * 
 */
package com.itests.fx.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jatinma
 *
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4440981470364720931L;

	@JsonProperty("AccountStatusDt")
	private String accountStatusDt;

	@JsonProperty("contact2Phone")
	private String contact2Phone;

	@JsonProperty("accountExternalIdType")
	private int accountExternalIdType;

	@JsonProperty("LanguageCode")
	private int languageCode;

	@JsonProperty("CustPhone1")
	private String custPhone1;

	@JsonProperty("CustPhone2")
	private String custPhone2;

	@JsonProperty("AccountStatus")
	private int accountStatus;

	@JsonProperty("CustCity")
	private String custCity;

	@JsonProperty("AccountExternalId")
	private String accountExternalId;

	@JsonProperty("AccountInternalId")
	private String accountInternalId;

	@JsonProperty("Key")
	private String key;

	/**
	 * @return the accountStatusDt
	 */
	public String getAccountStatusDt() {
		return accountStatusDt;
	}

	/**
	 * @param accountStatusDt
	 *            the accountStatusDt to set
	 */
	public void setAccountStatusDt(String accountStatusDt) {
		this.accountStatusDt = accountStatusDt;
	}

	/**
	 * @return the contact2Phone
	 */
	public String getContact2Phone() {
		return contact2Phone;
	}

	/**
	 * @param contact2Phone
	 *            the contact2Phone to set
	 */
	public void setContact2Phone(String contact2Phone) {
		this.contact2Phone = contact2Phone;
	}

	/**
	 * @return the accountExternalIdType
	 */
	public int getAccountExternalIdType() {
		return accountExternalIdType;
	}

	/**
	 * @param accountExternalIdType
	 *            the accountExternalIdType to set
	 */
	public void setAccountExternalIdType(int accountExternalIdType) {
		this.accountExternalIdType = accountExternalIdType;
	}

	/**
	 * @return the languageCode
	 */
	public int getLanguageCode() {
		return languageCode;
	}

	/**
	 * @param languageCode
	 *            the languageCode to set
	 */
	public void setLanguageCode(int languageCode) {
		this.languageCode = languageCode;
	}

	/**
	 * @return the custPhone1
	 */
	public String getCustPhone1() {
		return custPhone1;
	}

	/**
	 * @param custPhone1
	 *            the custPhone1 to set
	 */
	public void setCustPhone1(String custPhone1) {
		this.custPhone1 = custPhone1;
	}

	/**
	 * @return the custPhone2
	 */
	public String getCustPhone2() {
		return custPhone2;
	}

	/**
	 * @param custPhone2
	 *            the custPhone2 to set
	 */
	public void setCustPhone2(String custPhone2) {
		this.custPhone2 = custPhone2;
	}

	/**
	 * @return the accountStatus
	 */
	public int getAccountStatus() {
		return accountStatus;
	}

	/**
	 * @param accountStatus
	 *            the accountStatus to set
	 */
	public void setAccountStatus(int accountStatus) {
		this.accountStatus = accountStatus;
	}

	/**
	 * @return the custCity
	 */
	public String getCustCity() {
		return custCity;
	}

	/**
	 * @param custCity
	 *            the custCity to set
	 */
	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}

	/**
	 * @return the accountExternalId
	 */
	public String getAccountExternalId() {
		return accountExternalId;
	}

	/**
	 * @param accountExternalId
	 *            the accountExternalId to set
	 */
	public void setAccountExternalId(String accountExternalId) {
		this.accountExternalId = accountExternalId;
	}

	/**
	 * @return the accountInternalId
	 */
	public String getAccountInternalId() {
		return accountInternalId;
	}

	/**
	 * @param accountInternalId
	 *            the accountInternalId to set
	 */
	public void setAccountInternalId(String accountInternalId) {
		this.accountInternalId = accountInternalId;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

}
