package com.nazarmerza.quiz.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NT_MS")
public class Note extends Message {
	
	public Note(){
	}
	
	public Note(User sender, User receiver, String message) {
		super(sender, receiver, message);
	}

}
