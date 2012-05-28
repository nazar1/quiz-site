package com.nazarmerza.quiz.dao;

import java.util.List;

import com.nazarmerza.quiz.domain.Quiz;
import com.nazarmerza.quiz.domain.User;

public interface QuizDao extends GenericDao<Quiz, Long>{

	//public List<Quiz> getAllCreatedQuizes();
	//public List<Quiz> getAllTakenQuizes();

	public List<Quiz> getRecentlyCreatedQuizes(int maxResults);
	public List<Quiz> getRecentlyCreatedQuizes(User user, int maxResults);
	public List<Quiz> getAllUserCreatedQuizes(User user);
	//public List<Quiz> getRecentlyTakenQuizes(int maxResults);
	public List<Quiz> getPopularQuizes(int maxResults);
	
	public List<User> getHighestPerformers(int maxResults);
	
	
		
}
