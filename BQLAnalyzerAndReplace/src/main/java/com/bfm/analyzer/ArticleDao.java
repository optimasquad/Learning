package com.bfm.analyzer;

import java.util.List;

public interface ArticleDao {

	List<Article> listArticles();

	long updateArticle(long id);
}
