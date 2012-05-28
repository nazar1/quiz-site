package com.nazarmerza.quiz.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;

public interface GenericDao<T, I extends Serializable> {
	    
    public T findById(I id);
    public List<T> findByExample(T exampleInstance, String excludeProperty[]);
    public List<T> findAll();
    public void save(T entity);
    public void delete(T entity);
    public void delete(Long id);
    public int getDistinctCount();
    

}

