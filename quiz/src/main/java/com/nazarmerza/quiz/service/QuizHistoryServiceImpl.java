package com.nazarmerza.quiz.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nazarmerza.quiz.dao.QuizDao;
import com.nazarmerza.quiz.dao.QuizHistoryDao;
import com.nazarmerza.quiz.domain.Question;
import com.nazarmerza.quiz.domain.Quiz;
import com.nazarmerza.quiz.domain.QuizHistory;
import com.nazarmerza.quiz.domain.User;

@Repository
public class QuizHistoryServiceImpl implements QuizHistoryService {
	
	private int maxResult;
	private QuizHistoryDao quizHistoryDao;
	private QuizDao quizDao;

	
	@Autowired
	public QuizHistoryServiceImpl(QuizHistoryDao quizHistoryDao, QuizDao quizDao) {
		this.quizHistoryDao = quizHistoryDao;
		this.quizDao = quizDao;
	}
	
	public int getMaxResults() {
		return maxResult;
	}

	public void setMaxResults(int maxResults) {
		this.maxResult = maxResults;
	}
	
	@Transactional 
	public QuizHistory findQuizHistory(Long id){
		return quizHistoryDao.findById(id);
	}
	
	@Transactional
	public QuizHistory save(QuizHistory quizHistory){
		quizHistoryDao.save(quizHistory);
		return quizHistory;
	}
	
	@Transactional
	public void delete(QuizHistory quizHistory) {
		quizHistoryDao.delete(quizHistory.getId());
	}

	

	
	@Transactional
	public List<User> getHighestPerformers() {
		// TODO Auto-generated method stub
		return null;
	}


	@Transactional
	public List<QuizHistory> getRecentQuizTakingActivities() {
		return quizHistoryDao.getRecentQuizTakingHistory(maxResult * 5);
	}

	@Transactional
	public List<QuizHistory> getRecentQuizTakingActivities(User user) {
		return quizHistoryDao.getRecentQuizTakingHistory(user, maxResult);
	}

	public List<Quiz> getQuizesCreatedBy(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
