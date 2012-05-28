package com.nazarmerza.quiz.dao;

import java.util.List;

import com.nazarmerza.quiz.domain.Quiz;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.exception.AccountExistsException;
import com.nazarmerza.quiz.exception.AuthenticationFailedException;

public interface UserDao extends GenericDao<User, Long> {

	public User findByName(String userName);
	public User autheticate(User user) throws AuthenticationFailedException;
	public boolean userExists(User user);
	
	public List<User> getRecentlyCreatedUsers(int maxResults);

}
