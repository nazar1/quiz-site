package com.nazarmerza.quiz.dao;

import java.util.List;

import com.nazarmerza.quiz.domain.Message;
import com.nazarmerza.quiz.domain.Quiz;
import com.nazarmerza.quiz.domain.User;

public interface MessageDao extends GenericDao<Message, Long> {

	public List<Message> getAllUserMessage(User user);
	public List<Message> getActiveUserMessages(User user);
	public boolean friendRequestExists(Message message);
	public Message getFriendRequest(Message message);
}
