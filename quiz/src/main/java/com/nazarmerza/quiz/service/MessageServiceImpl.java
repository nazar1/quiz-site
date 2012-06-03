package com.nazarmerza.quiz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nazarmerza.quiz.dao.MessageDao;
import com.nazarmerza.quiz.dao.UserDao;
import com.nazarmerza.quiz.domain.Challenge;
import com.nazarmerza.quiz.domain.FriendRequest;
import com.nazarmerza.quiz.domain.Message;
import com.nazarmerza.quiz.domain.Note;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.domain.types.MessageType;
import com.nazarmerza.quiz.domain.types.Status;

@Repository
public class MessageServiceImpl implements MessageService {
	
	private UserDao userDao;
	private MessageDao messageDao;
	
	@Autowired
	public MessageServiceImpl(UserDao userDao, MessageDao messageDao) {
		this.userDao = userDao;
		this.messageDao = messageDao;
	}

	@Transactional
	public Message save(Message message) {
		messageDao.save(message);
		return message;
	}

	@Transactional
	public void delete(Message message) {
		messageDao.delete(message);
	}

	@Transactional
	public Message findMessage(Long id) {
		return messageDao.findById(id);
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
	public void approveFriendRequest(FriendRequest friendRequest) { 
		if (friendRequest == null) return;
		
		User user = userDao.findById(friendRequest.getReceiver().getId());
		User friend = userDao.findById(friendRequest.getSender().getId());
		user.addFriend(friend);
		friend.addFriend(user);
		userDao.save(user);
		userDao.save(friend);
		messageDao.delete(friendRequest);
		
	}

	@Transactional
	public void denyFriendRequest(FriendRequest friendRequest) {
		messageDao.delete(friendRequest);	
	}

	@Transactional
	public FriendRequest sendFriendRequest(FriendRequest friendRequest) {
		// check that there is no friend request already made by this user
		if (!messageDao.friendRequestExists(friendRequest)){
			messageDao.save(friendRequest);
		}
		return friendRequest;
	}

	@Transactional
	public Note sendNote(Note note) {
		messageDao.save(note);
		return note;
	}

	public Challenge sendChallenge(Challenge challenge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<FriendRequest> getFriendRequests(User user) {
		return messageDao.getUserFreindRequests(user);
	}

	@Transactional
	public List<Challenge> getChallenges(User user) {
		return messageDao.getUserChallenges(user);
	}

	@Transactional
	public List<Note> getNotes(User user) {
		return messageDao.getUserNotes(user);
	}
	
	
	/* utility methods */
	/*
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
	*/

}
