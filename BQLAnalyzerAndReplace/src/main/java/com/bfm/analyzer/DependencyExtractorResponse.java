/**
 * 
 */
package com.bfm.analyzer;

/**
 * @author jatin
 *
 */
public class DependencyExtractorResponse {

	// title and category and article id

	private String title;

	private String category;

	private long articleId;

	public DependencyExtractorResponse(String title, String category, long articleId) {

		this.title = title;
		this.category = category;
		this.articleId = articleId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the articleId
	 */
	public long getArticleId() {
		return articleId;
	}

	/**
	 * @param articleId the articleId to set
	 */
	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

}
