package com.nazarmerza.quiz.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

import com.nazarmerza.quiz.domain.types.Status;


@Entity
@Table(name = "ANNOUNCEMENT")
public class Announcement implements Serializable{
	
	private Long id;
	private User creator;
	private String announcement;
	private Status status;
	private Date created;
	
	public Announcement(){
		this.created = new Date();
		this.status = Status.ACTIVE;
	}
	
	public Announcement(User creator, String announcement){
		this();
		this.setCreator(creator);
		this.setAnnouncement(announcement);
	}
	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ANNOUNCEMENT_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false)
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	@NotEmpty(message = "Announcement Name must not be blank.")
	@Column(name = "ANNOUNCEMENT")
	public String getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}

	@Column(name = "STATUS")
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED")
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	
}
