package com.bfm.analyzer;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author imssbora
 *
 */
@Repository
@Transactional
public class ArticleDaoImp implements ArticleDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Article> listArticles() {
		// TODO Auto-generated method stub
		TypedQuery<Article> query = sessionFactory.getCurrentSession().createQuery("from Article");
		return query.getResultList();

	}

	@Override
	public long updateArticle(long id) {

		// Update the Article Now and save the results

		return 0;
	}

}
