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


	public boolean userExists(User user);

	
	public void createUserAccount(User user)
			throws UserNameExistsException, AccountExistsException;
	public void deleteUserAccount(User user);
	public User getUser(String userName);
	public User getUser(Long userId);
	public User getUser(User user);
	public List<User> getRecentUsers(int count);
	public void save(User user);
	
	
	/* friend related methods */
	public User findUser(String userName);
	public void approveFriendRequest(Message message);
	public void denyFriendRequest(Message message);
	public List<User> getUserFreinds(Long id);
	public User getUserFreind(Long userId, Long friendId);
	public List<String> getUserFriendsNames(Long id);

	
	/* Message-related methods */
	public void sendFriendRequest(Message message);
	public void sendNote(Message message);
	public void deleteMessage(Message message);
	public void activateMessage(Message message);
	public void deactivateMessage(Message message);
	public Message getMessage(Long id);
	public List<Message> getActiveUserMessages(User user);
	public List<Message> getFriendRequests(User user);
	public List<Message> getChallenges(User user);
	public List<Message> getNotes(User user);
	
	
	public List<Announcement> getActiveAnnouncements();
	//public User authenticateAccount(String userName, String password)
	//	throws AuthenticationException;

}
