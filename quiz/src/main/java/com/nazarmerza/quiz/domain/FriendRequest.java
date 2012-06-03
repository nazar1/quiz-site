package com.nazarmerza.quiz.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FR_MS")
public class FriendRequest extends Message{

	public FriendRequest(){}
	
	public FriendRequest(User sender, User receiver){
		super(sender, receiver, "");
		String message = "User, " + sender.getUserName() + " , wants to be friends with you.";
		setMessage(message);	
	}
}
