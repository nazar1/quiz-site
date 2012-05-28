package com.nazarmerza.quiz.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nazarmerza.quiz.dao.UserDao;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.exception.AccountExistsException;
import com.nazarmerza.quiz.exception.AuthenticationFailedException;
import com.nazarmerza.quiz.exception.MultipleUserAccount;

@Repository
public class UserDaoHibernate 
	extends GenericDaoHibernate<User, Long> 
	implements UserDao {

	
	@Transactional
	public User findByName(String userName) {
		String sql = "select * from user where USER_NAME = \"" + userName + "\"";
		//Query query = sessionFactory.getCurrentSession().createQuery(sql);
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(getPersistentClass());
		return (User) query.uniqueResult();
	}

	public User autheticate(User user) throws AuthenticationFailedException {
		User AuthUser = getUser(user);
		if (AuthUser == null) {
			throw new AuthenticationFailedException();
		}
		
		return AuthUser;
	}

	public boolean userExists(User user) {
		if (getUser(user) ==  null) {
			return false;
		}
		return true;
	}
	
	/**
	 * utility methods
	 * 
	 * @throws MultipleUserAccount 
	 */
	private User getUser(User user) {
		if (user == null) return null;
		if(user.getUserName().isEmpty() || user.getPassword().isEmpty()) {
			return null;
		}
		Query query = sessionFactory.getCurrentSession().createQuery(
    			"from user where USER_NAME = " + 
    			 user.getUserName() + " and PASSWORD " +
    					user.getPassword() );
		List<User> users = (List<User>) query.list();
		if (user == null) {
			return null;
		}
		if (users.size() != 1) {
			//throw new MultipleUserAccount("Multiple copy of the account exits.");
			return null;
		}
		
		return users.get(0);
	}
	
	public List<User> getRecentlyCreatedUsers(int maxResults){
		Criteria criteria = buildCriteria(null, Order.desc("created"));
		criteria.setMaxResults(maxResults);
		return criteria.list();
	}
	
	
}
