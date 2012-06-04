package com.nazarmerza.quiz.service;

import java.util.List;

import com.nazarmerza.quiz.domain.Announcement;
import com.nazarmerza.quiz.domain.Message;
import com.nazarmerza.quiz.domain.Profile;
import com.nazarmerza.quiz.domain.Quiz;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.exception.AccountExistsException;
import com.nazarmerza.quiz.exception.AuthenticationException;
import com.nazarmerza.quiz.exception.UserNameExistsException;
import com.nazarmerza.quiz.exception.UserNotFoundException;

public interface UserService {

	public User findUser(String userName);
	public User findUser(Long userId);
	public void delete(User user);
	public void save(User user);
	public List<User> getUserFreinds(User user);
	public void removeUserFriend(User user, User friend);
	public User getUserFreind(Long userId, Long friendId);
	public List<User> getRecentlyCreatedUsers(int maxResult);
	
	//public User getUserFreind(Long userId, Long friendId);
	//public List<User> getUserFreinds(Long userId);
	//public void removeFriend(User user, User friend);
	//public List<String> getUserFriendsNames(Long userId);
	//public void createUserAccount(User user)
	//		throws UserNameExistsException, AccountExistsException;
	//public User authenticateAccount(String userName, String password)
	//	throws AuthenticationException;

}
