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
	
	public boolean userExists(User user) {
		return userDao.userExists(user);
	}


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
	public List<User> getRecentUsers(int count){
		return userDao.getRecentlyCreatedUsers(10);
	}
	
	@Transactional
	public void save(User user){
		 userDao.save(user);
	}

	@Transactional
	public User findUser(String userName){
		return userDao.findByName(userName);
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
	
	@Transactional
	public List<String> getUserFriendsNames(Long id){
		User user = this.getUser(id);
		if (user == null) return null;
		List<User> friends =  this.getUserFreinds(id);
		if(friends == null) return null;
		
		List<String> friendsNames = new LinkedList<String>();
		for (User friend: friends) {
			friendsNames.add(friend.getUserName());
		}
		return friendsNames;
	}
	
	@Transactional
	public void approveFriendRequest(Message message) { 
		if (message == null ||
				message.getMessageType() != MessageType.FRIEND_REQUEST) {
			return;
		}
		User user = userDao.findById(message.getReceiver().getId());
		User friend = userDao.findById(message.getSender().getId());
		user.addFriend(friend);
		friend.addFriend(user);
		userDao.save(user);
		userDao.save(friend);
		messageDao.delete(message);
		
	}

	@Transactional
	public void denyFriendRequest(Message message) {
		messageDao.delete(message);
		
	}

	@Transactional
	public void sendFriendRequest(Message message) {
		// check that there is no friend request already made by this user
		if (!messageDao.friendRequestExists(message)){
			messageDao.save(message);
		}
	}
	
	@Transactional
	public void sendNote(Message message) {
		messageDao.save(message);
	}
	
	@Transactional
	public Message getMessage(Long id){
		return messageDao.findById(id);
	}

	@Transactional
	public void deleteMessage(Message message) {
		messageDao.delete(message);
	}

	@Transactional
	public void activateMessage(Message message) {
		message.setStatus(Status.ACTIVE);
		messageDao.save(message);
		
	}

	@Transactional
	public void deactivateMessage(Message message) {
		message.setStatus(Status.INACTIVE);
		messageDao.save(message);		
	}

	@Transactional
	public List<Message> getActiveUserMessages(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<Message> getFriendRequests(User user) {
		return getMessageByType(user, MessageType.FRIEND_REQUEST);
	}

	@Transactional
	public List<Message> getChallenges(User user) {
		return getMessageByType(user, MessageType.CHALLENGE);
	}

	@Transactional
	public List<Message> getNotes(User user) {
		return getMessageByType(user, MessageType.NOTE);
	}
	
	@Transactional
	private List<Message> getMessageByType(User user, MessageType messageType){
		List<Message> messages =  messageDao.getAllUserMessage(user);
		List<Message> messagesByType = new ArrayList<Message>();
		for(Message message: messages){
			if(message.getMessageType() == messageType){
				messagesByType.add(message);
			}
		}
		return messagesByType;
	}
	
	
	@Transactional
	public List<Announcement> getActiveAnnouncements(){
		return announcementDao.getActiveAnnouncements();
	}
}
