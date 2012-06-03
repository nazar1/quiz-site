package com.nazarmerza.quiz.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Time;
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
import javax.persistence.Transient;

@Entity
@Table(name = "QUIZ_HISTORY")
public class QuizHistory implements Serializable{
	
	private Long id;
	private Quiz quiz;
	private User user;
	private int correctAnswersNum;
	private Double score;
	private Long miliseconds;
	private Date created;
	
	public QuizHistory() {
		this.created = new Date();
	}
	
	
	public QuizHistory(Quiz quiz, User user, int correctAnswersNum,
			Long miliseconds) {

		this.quiz = quiz;
		this.user = user;
		this.correctAnswersNum = correctAnswersNum;
		this.score = calculateScore();
		this.miliseconds = miliseconds;
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "QHISTORY_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "QUIZ_ID")
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "CORRECT_ANSWERS_NUM")
	public int getCorrectAnswersNum() {
		return correctAnswersNum;
	}
	public void setCorrectAnswersNum(int correctAnswersNum) {
		this.correctAnswersNum = correctAnswersNum;
	}
	
	
	@Column(name = "SCORE")
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
	@Column(name = "MILI_SECONDS")
	public Long getMiliseconds() {
		return miliseconds;
	}

	public void setMiliseconds(Long miliseconds) {
		this.miliseconds = miliseconds;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED")
	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}
	
	@Transient
	public Time getTime(){
		return new Time(this.getMiliseconds());
	}
	
	//
	private Double calculateScore() {
		return 1.0;
	}
}
