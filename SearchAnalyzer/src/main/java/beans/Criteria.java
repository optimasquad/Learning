/**
 * 
 */
package beans;

import java.util.List;

/**
 * @author jatin
 *
 */
public class Criteria {

	private List<String> sourceFileNameRegExList;

	private String sourceDirPath;

	private boolean isDeepSearchRequired;

	private String dependencyMatchRegEx;

	private Long extractionTimeout;

	private String methodDependencyRegEx;

	/**
	 * @return the sourceFileNameRegExList
	 */
	public List<String> getSourceFileNameRegExList() {
		return sourceFileNameRegExList;
	}

	/**
	 * @param sourceFileNameRegExList
	 *            the sourceFileNameRegExList to set
	 */
	public void setSourceFileNameRegExList(List<String> sourceFileNameRegExList) {
		this.sourceFileNameRegExList = sourceFileNameRegExList;
	}

	/**
	 * @return the sourceDirPath
	 */
	public String getSourceDirPath() {
		return sourceDirPath;
	}

	/**
	 * @param sourceDirPath
	 *            the sourceDirPath to set
	 */
	public void setSourceDirPath(String sourceDirPath) {
		this.sourceDirPath = sourceDirPath;
	}

	/**
	 * @return the isDeepSearchRequired
	 */
	public boolean isDeepSearchRequired() {
		return isDeepSearchRequired;
	}

	/**
	 * @param isDeepSearchRequired
	 *            the isDeepSearchRequired to set
	 */
	public void setDeepSearchRequired(boolean isDeepSearchRequired) {
		this.isDeepSearchRequired = isDeepSearchRequired;
	}

	/**
	 * @return the dependencyMatchRegEx
	 */
	public String getDependencyMatchRegEx() {
		return dependencyMatchRegEx;
	}

	/**
	 * @param dependencyMatchRegEx
	 *            the dependencyMatchRegEx to set
	 */
	public void setDependencyMatchRegEx(String dependencyMatchRegEx) {
		this.dependencyMatchRegEx = dependencyMatchRegEx;
	}

	/**
	 * @return the extractionTimeout
	 */
	public Long getExtractionTimeout() {
		return extractionTimeout;
	}

	/**
	 * @param extractionTimeout
	 *            the extractionTimeout to set
	 */
	public void setExtractionTimeout(Long extractionTimeout) {
		this.extractionTimeout = extractionTimeout;
	}

	/**
	 * @return the methodDependencyRegEx
	 */
	public String getMethodDependencyRegEx() {
		return methodDependencyRegEx;
	}

	/**
	 * @param methodDependencyRegEx
	 *            the methodDependencyRegEx to set
	 */
	public void setMethodDependencyRegEx(String methodDependencyRegEx) {
		this.methodDependencyRegEx = methodDependencyRegEx;
	}

}
