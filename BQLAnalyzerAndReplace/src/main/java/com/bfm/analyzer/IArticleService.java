package com.bfm.analyzer;

import java.util.List;

public interface IArticleService {
	List<Article> getAllArticles();

	Article getArticleById(long articleId);

	void updateArticle(Article article);

}
