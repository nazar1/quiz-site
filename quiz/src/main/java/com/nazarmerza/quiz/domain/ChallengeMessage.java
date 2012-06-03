package com.nazarmerza.quiz.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.nazarmerza.quiz.domain.types.MessageType;

@Entity
@DiscriminatorValue("CH_MS")
public class ChallengeMessage extends Message {
	
	private static final String CHALLENGE_MESSAGE = 
			"You have been challenged to take this quiz by user, ";
	
	private Quiz quiz;
	
	public ChallengeMessage(){
	}
	
	public ChallengeMessage(User sender, User receiver,
			Quiz quiz) {
		super(sender, receiver, CHALLENGE_MESSAGE);
		this.quiz = quiz;
	}

	@ManyToOne
	@JoinColumn(name="QUIZ_ID", nullable = false)
	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	
	
	

}
