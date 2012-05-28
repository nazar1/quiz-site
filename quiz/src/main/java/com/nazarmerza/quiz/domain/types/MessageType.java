package com.nazarmerza.quiz.domain.types;

public enum MessageType {

	FRIEND_REQUEST,
	CHALLENGE,
	NOTE;
	
	public String getName(){
		return name();
	}
}
