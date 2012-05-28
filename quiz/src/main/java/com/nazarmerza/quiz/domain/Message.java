package com.nazarmerza.quiz.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.nazarmerza.quiz.domain.types.MessageType;
import com.nazarmerza.quiz.domain.types.Status;

@Entity
@Table(name = "MESSAGE")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="MSG_TYPE")
public class Message implements Serializable{
	
	protected Long id;
	protected User sender;
	protected User receiver;
	protected Status status;
	protected MessageType messageType;
	protected String message;
	protected Date created;
	
	public Message () {
		this.status = Status.ACTIVE;
		this.created = new Date();
	}
	
	
	public Message(User sender, User receiver,
			MessageType messageType) {
		
		this();
		this.sender = sender;
		this.receiver = receiver;
		this.setMessageType(messageType);
		
		// this.setMessage(message);
	
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "MESSAGE_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "STATUS", nullable=false)
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Column(name = "MESSAGE_TYPE", nullable=false)
	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
		
		// set default message
		switch(messageType) {
		case FRIEND_REQUEST:
			this.setMessage("You have a friend request");
			break;
		case CHALLENGE:
			this.setMessage("friendChallenge.message");
			break;
		case NOTE:
			this.setMessage("friendNote.message");
		default:
			break;
		}
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED", nullable=false)
	public Date getCreated() {
		return created;
	}

	@ManyToOne
	@JoinColumn(name = "SENDER_ID", nullable=false)
	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	@ManyToOne
	@JoinColumn(name = "RECEIVER_ID", nullable=false)
	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	@Column(name="MESSAGE", nullable=false)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
}
