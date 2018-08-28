/**
 * 
 */
package com.itests.fx.model;

/**
 * @author jatinma
 * 
 *         This is the class to map the results of Failure of the test cases
 * 
 * 
 *
 */
public class FailureResponse {

	private String message;

	private String className;

	private String testHeader;

	private String trace;

	public FailureResponse(String className, String message, String testHeader, String trace) {

		this.className = className;
		this.message = message;
		this.testHeader = testHeader;
		this.trace = trace;

	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className
	 *            the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the testHeader
	 */
	public String getTestHeader() {
		return testHeader;
	}

	/**
	 * @param testHeader
	 *            the testHeader to set
	 */
	public void setTestHeader(String testHeader) {
		this.testHeader = testHeader;
	}

	/**
	 * @return the trace
	 */
	public String getTrace() {
		return trace;
	}

	/**
	 * @param trace
	 *            the trace to set
	 */
	public void setTrace(String trace) {
		this.trace = trace;
	}

}
