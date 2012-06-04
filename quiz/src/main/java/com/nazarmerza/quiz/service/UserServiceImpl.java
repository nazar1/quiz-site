package com.nazarmerza.quiz.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nazarmerza.quiz.dao.AnnouncementDao;
import com.nazarmerza.quiz.dao.MessageDao;
import com.nazarmerza.quiz.dao.QuizDao;
import com.nazarmerza.quiz.dao.UserDao;
import com.nazarmerza.quiz.domain.Announcement;
import com.nazarmerza.quiz.domain.Message;
import com.nazarmerza.quiz.domain.Profile;
import com.nazarmerza.quiz.domain.Quiz;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.domain.types.MessageType;
import com.nazarmerza.quiz.domain.types.Status;
import com.nazarmerza.quiz.exception.AccountExistsException;
import com.nazarmerza.quiz.exception.AuthenticationException;
import com.nazarmerza.quiz.exception.UserNameExistsException;
import com.nazarmerza.quiz.exception.UserNotFoundException;

@Repository
public class UserServiceImpl implements UserService{

	private UserDao userDao;
	private QuizDao quizDao;
	private MessageDao messageDao;
	private AnnouncementDao announcementDao;
	
	@Autowired
	public UserServiceImpl(UserDao userDao, QuizDao quizDao, MessageDao messageDao
			, AnnouncementDao announcementDao) {
		this.userDao = userDao;
		this.quizDao = quizDao;
		this.messageDao = messageDao;
		this.announcementDao = announcementDao;
	}
	

	/**************************************************/
	@Transactional
	public User findUser(String userName){
		return userDao.findByName(userName);
	}
	
	@Transactional
	public User findUser(Long userId) {
		return userDao.findById(userId);
	}

	@Transactional
	public void save(User user){
		 userDao.save(user);
	}

	@Transactional
	public void delete(User user) {
		userDao.delete(user.getId());
	}

	@Transactional
	public List<User> getUserFreinds(User user) {
		if (user == null) return null;
		user = userDao.findById(user.getId());
		List<User> friends = user.getFriends();
		List<User> FriendsList =  new LinkedList<User>();
		for (User friend: friends) {
			FriendsList.add(friend);
		}
		return  FriendsList;

	}
	
	@Transactional
	public void removeUserFriend(User user, User friend){
		if (user == null) return;
		
		// reload users, to have their friends collection available.
		user = userDao.findById(user.getId());
		friend = userDao.findById(friend.getId());
		
		user.removeFriend(friend);
		friend.removeFriend(user);
		userDao.save(user);
		userDao.save(friend);
	}
	
	@Transactional
	public List<User> getRecentlyCreatedUsers(int maxResult){
		return userDao.getRecentlyCreatedUsers(maxResult);
	}
	/*
	public List<User> getUserFreinds(User user) {
		if (user == null) return null;
		List<User> friends = user.getFriends();
		List<User> FriendsList =  new LinkedList<User>();
		for (User friend: friends) {
			FriendsList.add(friend);
		}
		return  FriendsList;
	}
	*/
	
	/**************************************************/
	

	@Transactional
	public void createUserAccount(User user) 
			throws UserNameExistsException,
			AccountExistsException {
		userDao.save(user);
	}

	@Transactional
	public void deleteUserAccount(User user) {
		userDao.delete(user.getId());
		
	}
	
	@Transactional
	public User getUser(String userName){
		return userDao.findByName(userName);
	}


	@Transactional
	public User getUser(Long userId) {
		return userDao.findById(userId);
	}

	@Transactional
	public User getUser(User user) {
		return userDao.findById(user.getId());
	}
	
	
	
	


	@Transactional 
	public List<User> getUserFreinds(Long id){
		User user = this.getUser(id);
		if (user == null) return null;
		List<User> friends = user.getFriends();
		List<User> FriendsList =  new LinkedList<User>();
		for (User friend: friends) {
			FriendsList.add(friend);
		}
		return  FriendsList;
		
	}
	@Transactional 
	public User getUserFreind(Long userId, Long friendId){
		List<User> friends =  this.getUserFreinds(userId);
		if(friends == null) return null;
		for (User friend: friends) {
			if (friend.getId().equals(friendId)) {
				return friend;
			}
		}
		return null;
	}
	


	
	
}
