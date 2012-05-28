package com.nazarmerza.quiz.dao.hibernate;

import java.io.Serializable;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.nazarmerza.quiz.dao.GenericDao;
import com.nazarmerza.quiz.domain.Quiz;

import java.lang.reflect.ParameterizedType;

public abstract class GenericDaoHibernate<T, I extends Serializable> 	
	implements GenericDao<T, I> {

	
	private Class<T> persistentClass;
	private Session session;
	protected SessionFactory sessionFactory;
    
	/*
	 * Initialization methods
	 */
 
    public GenericDaoHibernate() {
        this.persistentClass = 
        		(Class<T>) 
        		((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
     }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
 	protected Session getSession(){
 		if (session == null) {
 			session = sessionFactory.getCurrentSession();
 		}
 		
 		return session;
 	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}


	/*
	 * Dao methods
	 *
	 */
	

	
    @SuppressWarnings("unchecked")
	public T findById(I id){
    	return (T) sessionFactory.getCurrentSession().get(getPersistentClass(), id);
   }
    
    public List<T> findByExample(T exampleInstance, String excludeProperty[]) {

    	Example example = Example.create(exampleInstance);
    	for (String exclude: excludeProperty){
    		example.excludeProperty(exclude);
    	}

    	Criteria criteria = getSession().createCriteria(getPersistentClass());
    	criteria.add(example);
    	return criteria.list();
    
    }
    
    public List<T> findAll() {
    	//return getHibernateTemplate().loadAll(getPersistentClass());
    	Query query = sessionFactory.getCurrentSession().createQuery(
    			"from " + getPersistentClass().getName());
    	return query.list();
  
    }
    
    public void save(T entity){
    	sessionFactory.getCurrentSession().saveOrUpdate(entity);

    }
    
    public void delete(T entity){
    	sessionFactory.getCurrentSession().delete(entity);
    }
    
    public void delete(I id) {
    	T entity = findById(id);
    	if (entity != null) {
    		this.delete(entity);
    	}
    			
    }
    
    public int getDistinctCount(){
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(getPersistentClass())
		.setProjection(Projections.countDistinct("id"));
		return ((Integer)criteria.list().get(0)).intValue();
	}
    
    // helper methods
 	protected Criteria buildCriteria(Criterion criterion, Order order){
 		Criteria criteria = 
 				sessionFactory.getCurrentSession().createCriteria(getPersistentClass());
 		
 		if (criterion != null) {
 			criteria.add(criterion);
 		}
 		if (order != null) {
 			criteria.addOrder(order);
 		}
 		return criteria;
 	}
 	


}
