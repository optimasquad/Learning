/**
 * 
 */
package beans;

/**
 * @author jatin
 *
 */
public class DependencyExtractorResponse {

	private String matchedDependencyFileName;

	private String numberOfMethods;

	private String numberOfLines;

	/**
	 * @return the matchedDependencyFileName
	 */
	public String getMatchedDependencyFileName() {
		return matchedDependencyFileName;
	}

	/**
	 * @param matchedDependencyFileName
	 *            the matchedDependencyFileName to set
	 */
	public void setMatchedDependencyFileName(String matchedDependencyFileName) {
		this.matchedDependencyFileName = matchedDependencyFileName;
	}

	/**
	 * @return the numberOfMethods
	 */
	public String getNumberOfMethods() {
		return numberOfMethods;
	}

	/**
	 * @param numberOfMethods
	 *            the numberOfMethods to set
	 */
	public void setNumberOfMethods(String numberOfMethods) {
		this.numberOfMethods = numberOfMethods;
	}

	/**
	 * @return the numberOfLines
	 */
	public String getNumberOfLines() {
		return numberOfLines;
	}

	/**
	 * @param numberOfLines
	 *            the numberOfLines to set
	 */
	public void setNumberOfLines(String numberOfLines) {
		this.numberOfLines = numberOfLines;
	}

}
