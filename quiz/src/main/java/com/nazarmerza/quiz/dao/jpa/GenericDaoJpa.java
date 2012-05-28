package com.nazarmerza.quiz.dao.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContexts;

import org.springframework.beans.factory.annotation.Autowired;

import com.nazarmerza.quiz.dao.GenericDao;


public abstract class GenericDaoJpa <T extends Serializable>
	implements GenericDao<T, Long> {
	
	protected EntityManager entityManager;
	private Class<T> persistentClass;
    
	/*
	 * Initialization methods
	 */
 
    public GenericDaoJpa() {
        this.persistentClass = 
        		(Class<T>) 
        		((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
     }
    
    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
