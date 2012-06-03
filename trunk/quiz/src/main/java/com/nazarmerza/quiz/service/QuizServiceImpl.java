package com.nazarmerza.quiz.service;

import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nazarmerza.quiz.dao.QuizDao;
import com.nazarmerza.quiz.dao.QuizHistoryDao;
import com.nazarmerza.quiz.dao.UserDao;
import com.nazarmerza.quiz.dao.hibernate.QuizHistoryDaoHibernate;
import com.nazarmerza.quiz.domain.Question;
import com.nazarmerza.quiz.domain.Quiz;
import com.nazarmerza.quiz.domain.QuizHistory;
import com.nazarmerza.quiz.domain.User;

@Repository
public class QuizServiceImpl implements QuizService{

	private QuizDao quizDao;
	private QuizHistoryDao quizHistoryDao;
	private static final int MAX_COUNT = 5;
	
	@Autowired
	public QuizServiceImpl(QuizDao quizDao, QuizHistoryDao quizHistoryDao) {
		this.quizDao = quizDao;
		this.quizHistoryDao = quizHistoryDao;
	}

	@Transactional
	public Quiz save(Quiz quiz) {
		quizDao.save(quiz);
		return quiz;
	}
	
	@Transactional
	public void delete(Quiz quiz) {
		quizDao.delete(quiz.getId());
	}

	@Transactional
	public Quiz findQuiz(Long id) {
		return quizDao.findById(id);
	}
	
	@Transactional
	public List<Quiz> getPopularQuizes() {
		// find N quizzes with max occuring count
		List<Long> quizIds = quizHistoryDao.findFrequentlyTakenQuizzes(MAX_COUNT);
		// get these quizzes from quizDao
		List<Quiz> popularQuizzes = new LinkedList<Quiz>();
		
		for (Long id: quizIds){
			Quiz quiz = quizDao.findById(id);
			if (quiz != null) {
				popularQuizzes.add(quiz);
			}
		}
		return popularQuizzes;
	}
	

	@Transactional
	public List<Quiz> getRecentlyCreatedQuizes() {
		return quizDao.getRecentlyCreatedQuizes(MAX_COUNT);
	}
	
	@Transactional
	public List<Quiz> getRecentlyCreatedQuizes(User user) {
		return quizDao.getRecentlyCreatedQuizes(user, MAX_COUNT);
	}
	
	@Transactional
	public List<Quiz> getRecentlyTakenQuizes() {
		return quizDao.getRecentlyCreatedQuizes(MAX_COUNT);
	}

	
}
