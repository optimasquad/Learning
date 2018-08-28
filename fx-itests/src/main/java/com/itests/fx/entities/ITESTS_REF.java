/**
 * 
 */
package com.itests.fx.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jatinma
 *
 */

public class ITESTS_REF implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5565497629385553156L;

	private Long id;

	private String itests_key;

	private String itests_value;

	private Date currentTime;

	public ITESTS_REF(Long id, String itests_key, String itests_value, Date currentTime) {
		super();
		this.id = id;
		this.itests_key = itests_key;
		this.itests_value = itests_value;
		this.currentTime = currentTime;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the itests_key
	 */
	public String getItests_key() {
		return itests_key;
	}

	/**
	 * @param itests_key
	 *            the itests_key to set
	 */
	public void setItests_key(String itests_key) {
		this.itests_key = itests_key;
	}

	/**
	 * @return the itests_value
	 */
	public String getItests_value() {
		return itests_value;
	}

	/**
	 * @param itests_value
	 *            the itests_value to set
	 */
	public void setItests_value(String itests_value) {
		this.itests_value = itests_value;
	}

	/**
	 * @return the currentTime
	 */
	public Date getCurrentTime() {
		return currentTime;
	}

	/**
	 * @param currentTime
	 *            the currentTime to set
	 */
	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}

	public ITESTS_REF() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ITESTS_REF [id=" + id + ", itests_key=" + itests_key + ", itests_value=" + itests_value
				+ ", currentTime=" + currentTime + "]";
	}

}
