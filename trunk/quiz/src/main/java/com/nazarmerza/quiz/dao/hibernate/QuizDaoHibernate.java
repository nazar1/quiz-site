package com.nazarmerza.quiz.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nazarmerza.quiz.dao.QuizDao;
import com.nazarmerza.quiz.domain.Quiz;
import com.nazarmerza.quiz.domain.User;


@Repository
public class QuizDaoHibernate 
	extends GenericDaoHibernate<Quiz, Long>
	implements QuizDao{

	
	public List<Quiz> getPopularQuizes(int maxResults) {
		Criteria criteria = buildCriteria(null, Order.desc("rating"));
		criteria.setMaxResults(maxResults);
		return criteria.list();
	}

	public List<Quiz> getRecentlyCreatedQuizes(int maxResults) {
		Criteria criteria = buildCriteria(null, Order.desc("created"));
		criteria.setMaxResults(maxResults);
		return criteria.list();

	}
	

	public List<Quiz> getRecentlyCreatedQuizes(User user, int maxResults) {
		Criteria criteria = buildCriteria(
				Restrictions.eq("creator.id", user.getId()),
				Order.desc("created"));
		criteria.setMaxResults(maxResults);
		return criteria.list();
	}
	
	public List<Quiz> getAllUserCreatedQuizes(User user) {
		Criteria criteria = buildCriteria(
				Restrictions.eq("creator.id", user.getId()),
				Order.desc("created"));
		return criteria.list();
	}

	public List<User> getHighestPerformers(int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}

}
