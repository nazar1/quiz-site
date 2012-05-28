package com.nazarmerza.quiz.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nazarmerza.quiz.dao.QuizHistoryDao;
import com.nazarmerza.quiz.domain.QuizHistory;
import com.nazarmerza.quiz.domain.User;

@Repository
public class QuizHistoryDaoHibernate 
	extends GenericDaoHibernate<QuizHistory, Long>
	implements QuizHistoryDao{

	public List<QuizHistory> getRecentQuizTakingHistory(User user, int maxResult) {
		Criteria criteria = buildCriteria(
				Restrictions.eq("user.id", user.getId()),
				Order.desc("created"));
		criteria.setMaxResults(maxResult);
		return criteria.list();
	}

	public List<QuizHistory> getRecentQuizTakingHistory(int maxResult) {
		Criteria criteria = buildCriteria(null, Order.desc("created"));
		criteria.setMaxResults(maxResult);
		return criteria.list();
	}

}
