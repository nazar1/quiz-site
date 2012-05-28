package com.nazarmerza.quiz.service;

import java.sql.Time;
import java.util.List;

import com.nazarmerza.quiz.domain.Question;
import com.nazarmerza.quiz.domain.Quiz;
import com.nazarmerza.quiz.domain.QuizHistory;
import com.nazarmerza.quiz.domain.User;

public interface QuizService {
	
	public void createQuiz(Quiz quiz);
	public Quiz getQuiz(Long id);
		
	public List<Quiz> getPopularQuizes();
	public List<Quiz> getRecentlyCreatedQuizes();
	public List<Quiz> getRecentlyCreatedQuizes(User user);
	//public List<Quiz> getRecentlyTakenQuizes();
	//public List<Quiz> getUserTakenQuizes(User user);
	
	public List<User> getHighestPerformers();
	
	public void recordQuizHistory(Quiz quiz, List<Question> answeredQuestions, 
			Long completionTime,User user);
	public List<QuizHistory> getRecentQuizTakingActivities();
	public List<QuizHistory> getRecentQuizTakingActivities(User user);
}
