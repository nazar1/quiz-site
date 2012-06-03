package com.nazarmerza.quiz.dao;

import java.util.List;

import com.nazarmerza.quiz.domain.QuizHistory;
import com.nazarmerza.quiz.domain.User;

public interface QuizHistoryDao extends GenericDao<QuizHistory, Long>{
	
	public List<Long> findFrequentlyTakenQuizzes(int maxResult);
	
	public List<QuizHistory> getRecentQuizTakingHistory(User user, int maxResult);
	public List<QuizHistory> getRecentQuizTakingHistory(int maxResult);
}
