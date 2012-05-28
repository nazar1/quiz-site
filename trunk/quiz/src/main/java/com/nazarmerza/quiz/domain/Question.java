package com.nazarmerza.quiz.domain;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.JoinColumn;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.nazarmerza.quiz.domain.types.QuestionType;
import com.nazarmerza.quiz.domain.validation.Answer;

//@MappedSuperclass
@Entity
@Table(name = "question")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="QSTN_TYPE")
public abstract class Question implements Serializable{
	
	protected Long id;
	protected QuestionType type;
	protected String answer;
	//protected int order;
	protected Quiz Quiz;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "QUESTION_ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "TYPE", nullable = false)
	public QuestionType getType() {
		return type;
	}
	
	public void setType(QuestionType type) {
		this.type = type;
	}

	@NotEmpty(message = "Answer must not be empty", groups=Answer.class)
	//@NotEmpty(message = "Answer must not be empty")
	@Column(name = "ANSWER", length = 100, nullable = false)
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/*
	@Column(name = "ORDER")
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	*/

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "QUIZ_ID")
	public Quiz getQuiz() {
		return Quiz;
	}
	public void setQuiz(Quiz quiz) {
		Quiz = quiz;
	}
	
	
	/**
	 * 
	 * @param reponse
	 * @return
	 */
	public  boolean validateResponse(String reponse){
		return false;
	}

	
	
}
