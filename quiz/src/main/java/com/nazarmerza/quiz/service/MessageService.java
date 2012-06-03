package com.nazarmerza.quiz.service;

import java.util.List;

import com.nazarmerza.quiz.domain.Challenge;
import com.nazarmerza.quiz.domain.FriendRequest;
import com.nazarmerza.quiz.domain.Message;
import com.nazarmerza.quiz.domain.Note;
import com.nazarmerza.quiz.domain.User;

public interface MessageService {

	
	/* */
	public Message save(Message message);
	public void delete(Message message);
	public Message findMessage(Long id);
	
	public void activateMessage(Message message);
	public void deactivateMessage(Message message);
	
	public void approveFriendRequest(FriendRequest friendRequest);
	public void denyFriendRequest(FriendRequest friendRequest);
	
	public FriendRequest sendFriendRequest(FriendRequest friendRequest);
	public Note sendNote(Note note);
	public Challenge sendChallenge(Challenge challenge);

	public List<FriendRequest> getFriendRequests(User user);
	public List<Challenge> getChallenges(User user);
	public List<Note> getNotes(User user);
	
	//public List<Message> getActiveUserMessages(User user);

}
