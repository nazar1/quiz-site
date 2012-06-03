package com.nazarmerza.quiz.dao;

import java.util.List;

import com.nazarmerza.quiz.domain.Challenge;
import com.nazarmerza.quiz.domain.FriendRequest;
import com.nazarmerza.quiz.domain.Message;
import com.nazarmerza.quiz.domain.Note;
import com.nazarmerza.quiz.domain.Quiz;
import com.nazarmerza.quiz.domain.User;

public interface MessageDao extends GenericDao<Message, Long> {

	public List<FriendRequest> getUserFreindRequests(User user);
	public List<Challenge> getUserChallenges(User user);
	public List<Note> getUserNotes(User user);
	
	public List<Message> getActiveUserMessages(User user);
	public boolean friendRequestExists(Message message);
	public Message getFriendRequest(Message message);
}
