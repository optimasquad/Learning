package com.itests.fx.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NCAResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8912799342933184341L;

	@JsonProperty("Output")
	private Output Output;

	public Output getOutput() {
		return this.Output;
	}

	public void setOutput(Output Output) {
		this.Output = Output;
	}

	private int TotalCount;

	public int getTotalCount() {
		return this.TotalCount;
	}

	public void setTotalCount(int TotalCount) {
		this.TotalCount = TotalCount;
	}

	private String ServerDate;

	public String getServerDate() {
		return this.ServerDate;
	}

	public void setServerDate(String ServerDate) {
		this.ServerDate = ServerDate;
	}

	private String ProductVersion;

	public String getProductVersion() {
		return this.ProductVersion;
	}

	public void setProductVersion(String ProductVersion) {
		this.ProductVersion = ProductVersion;
	}

	private int ServerId;

	public int getServerId() {
		return this.ServerId;
	}

	public void setServerId(int ServerId) {
		this.ServerId = ServerId;
	}

}
