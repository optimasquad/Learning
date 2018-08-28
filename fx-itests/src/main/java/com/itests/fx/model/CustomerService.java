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
public class CustomerService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("ParentAccountInternalId")
	private String parentAccountInternalId;

	@JsonProperty("ServiceInternalId")
	private String serviceInternalId;

	@JsonProperty("ServiceInternalIdResets")
	private String serviceInternalIdResets;

	/**
	 * @return the parentAccountInternalId
	 */
	public String getParentAccountInternalId() {
		return parentAccountInternalId;
	}

	/**
	 * @param parentAccountInternalId
	 *            the parentAccountInternalId to set
	 */
	public void setParentAccountInternalId(String parentAccountInternalId) {
		this.parentAccountInternalId = parentAccountInternalId;
	}

	/**
	 * @return the serviceInternalId
	 */
	public String getServiceInternalId() {
		return serviceInternalId;
	}

	/**
	 * @param serviceInternalId
	 *            the serviceInternalId to set
	 */
	public void setServiceInternalId(String serviceInternalId) {
		this.serviceInternalId = serviceInternalId;
	}

	/**
	 * @return the serviceInternalIdResets
	 */
	public String getServiceInternalIdResets() {
		return serviceInternalIdResets;
	}

	/**
	 * @param serviceInternalIdResets
	 *            the serviceInternalIdResets to set
	 */
	public void setServiceInternalIdResets(String serviceInternalIdResets) {
		this.serviceInternalIdResets = serviceInternalIdResets;
	}

}
