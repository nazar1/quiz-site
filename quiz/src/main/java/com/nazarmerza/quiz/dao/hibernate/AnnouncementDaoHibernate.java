package com.nazarmerza.quiz.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nazarmerza.quiz.dao.AnnouncementDao;
import com.nazarmerza.quiz.dao.QuizDao;
import com.nazarmerza.quiz.domain.Announcement;
import com.nazarmerza.quiz.domain.Quiz;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.domain.types.Status;

@Repository
public class AnnouncementDaoHibernate 
	extends GenericDaoHibernate<Announcement, Long>
	implements AnnouncementDao{

	public List<Announcement> getActiveAnnouncements() {
		return buildCriteria(Restrictions.eq("status", Status.ACTIVE), null).list();
	}

	public int getActiveAnnouncementsCount() {	
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("status", Status.ACTIVE));
		criteria.setProjection(Projections.rowCount());
		return ((Integer)criteria.list().get(0)).intValue();

	}

}
