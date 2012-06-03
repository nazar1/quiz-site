package com.nazarmerza.quiz.service;

import java.util.List;

import com.nazarmerza.quiz.domain.Quiz;
import com.nazarmerza.quiz.domain.QuizHistory;
import com.nazarmerza.quiz.domain.User;

public interface QuizHistoryService {
		
	public QuizHistory findQuizHistory(Long id);
	public QuizHistory save(QuizHistory quizHistory);
	public void delete(QuizHistory quizHistory);
	
	public List<User> getHighestPerformers();
	
}
