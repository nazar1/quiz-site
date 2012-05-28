package com.nazarmerza.quiz.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name = "USER")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String userName;
	private String password;
	private String email;
	private String securityQuestion;
	private String securityAnswer;
	private String authority;
	private Profile profile;
	private Date created;
	private List<User> friends = new ArrayList<User>();


	public User() {
		this.created = new Date();
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "USER_ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty(message = "User Name must not be blank.")
	@Size(min = 2, max = 20, message = "User Name must be between 2 and 20 characters.")
	@Column(name = "USER_NAME", length = 100, nullable = false, unique = true)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@NotEmpty(message = "Password must not be blank.")
	@Column(name = "PASSWORD", length = 100, nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotEmpty
	@Size(max=45)
	@Column(name = "AUTHORITY", length = 45, nullable = false)
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	@NotEmpty(message = "Email must not be blank.")
	@Email
	@Column(name = "EMAIL", length = 255, nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Transient
	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	@Transient
	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Embedded
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
		//profile.setUser(this);
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USER_FRIEND",
			joinColumns  = @JoinColumn(name = "USER_ID"),
			inverseJoinColumns = @JoinColumn(name = "FRIEND_ID")
			)
	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}
	
	/* */
	
	public void addFriend(User friend){
		this.friends.add(friend);
		//friend.addFriend(this);
	}
	
	/*
	//@Embedded
	//@OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user", 
			cascade = CascadeType.ALL)
	//@PrimaryKeyJoinColumn
	public Profile getProfile() {
		return profile;
	}
	
	*/







}
