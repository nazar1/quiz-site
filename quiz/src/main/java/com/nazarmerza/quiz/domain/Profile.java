package com.nazarmerza.quiz.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;

import com.nazarmerza.quiz.domain.types.UserRank;
@Embeddable
//@Entity
//@Table(name = "PROFILE")
public class Profile implements Serializable{
	
	//private Long id;
	//private User user;
	private UserRank rank;
	//private List<Quiz> createdQuizes = new ArrayList<Quiz>();
	
	public Profile(){
		this.rank = UserRank.AMATEUR_AUTHOR;
	}
	
	
	@Column(name = "USER_RANK")
	public UserRank getRank() {
		return rank;
	}

	public void setRank(UserRank rank) {
		this.rank = rank;
	}

	
	
	

	/* getters and setters */
	
	/*
	 * 
	 * 
	 * @OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public User getUser() {
		return user;
	}
	public void incrementQuizTakenCount(){
		this.quizTaken += 1;
	}
	
	public void incementQuizCreatedCount(){
		this.quizCreated += 1;
	}
	@Id
	@GeneratedValue(generator="generator")
	@GenericGenerator(
			name="generator", 
			strategy = "foreign", 
			parameters= @Parameter(name="property", value="user")
		  )
	@Column(name = "PROFILE_ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	
	*/
	

}
