package com.nazarmerza.quiz.service;

import java.sql.Time;
import java.util.List;

import com.nazarmerza.quiz.domain.Question;
import com.nazarmerza.quiz.domain.Quiz;
import com.nazarmerza.quiz.domain.QuizHistory;
import com.nazarmerza.quiz.domain.User;

public interface QuizService {
	
	public Quiz findQuiz(Long id);
	public Quiz save(Quiz quiz);
	public void delete(Quiz quiz);
	
	public List<Quiz> getRecentlyCreatedQuizes();
	public List<Quiz> getPopularQuizes();
	public List<Quiz> getRecentlyCreatedQuizes(User user);

		
	//public List<QuizHistory> getRecentQuizTakingActivities();
	//public List<QuizHistory> getRecentQuizTakingActivities(User user);
	//public void saveQuizHistory(Quiz quiz, List<Question> answeredQuestions, 
	//		Long completionTime,User user);
	//public List<Quiz> getRecentlyTakenQuizes();
	//public List<Quiz> getUserTakenQuizes(User user);
}
