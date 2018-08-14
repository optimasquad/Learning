package com.bfm.analyzer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService implements IArticleService {

	@Autowired
	ArticleDao articleDao;

	@Override
	public Article getArticleById(long articleId) {
		return null;
	}

	@Override
	public List<Article> getAllArticles() {
		return articleDao.listArticles();

	}

	@Override
	public void updateArticle(Article article) {
		// articleRepository.save(article);
	}

}
