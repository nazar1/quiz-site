package com.nazarmerza.quiz.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.nazarmerza.quiz.dao.QuestionDao;
import com.nazarmerza.quiz.domain.Question;

@Repository
public class QuestionDaoHibernate 
	extends GenericDaoHibernate<Question, Long>
	implements QuestionDao{

}
