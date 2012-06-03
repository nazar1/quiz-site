package com.nazarmerza.quiz.domain;

import static javax.persistence.GenerationType.IDENTITY;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Fetch;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.AutoPopulatingList;

import com.nazarmerza.quiz.domain.types.Category;
import com.nazarmerza.quiz.util.QuestionFactory;
import org.springframework.util.AutoPopulatingList.ElementFactory;
import org.springframework.util.AutoPopulatingList.ElementInstantiationException;
@Entity
@Table(name = "QUIZ")
public class Quiz implements Serializable{
	
	private Long id;
	private String quizTitle;
	private String description;
	private Category category;
	
	private boolean random;
	private boolean multipage;
	private boolean immediateCorrection;
	private boolean practiceMode;
	
	private int rating;
	private int numberOfQuestion;
	private List<Question> questions = new ArrayList<Question>();
	private User creator;
	private Date created;
	
	
	public Quiz(){
		this.created = new Date();
		this.rating = 1;
	}
	
	

	public Quiz(String quizTitle, String description,
			Category category, boolean random, boolean multipage,
			boolean immediateCorrection, boolean practiceMode, int rating,
			int numberOfQuestion, List<Question> questions, User creator,
			Date created) {
		
		this.quizTitle = quizTitle;
		this.description = description;
		this.category = category;
		this.random = random;
		this.multipage = multipage;
		this.immediateCorrection = immediateCorrection;
		this.practiceMode = practiceMode;
		this.rating = rating;
		this.numberOfQuestion = numberOfQuestion;
		this.questions = questions;
		this.creator = creator;
		this.created = created;
	}



	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "QUIZ_ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	@NotEmpty(message = "Quiz title must not be blank.")
	@Size(min = 2, max = 100)
	@Column(name = "TITLE", length = 100)
	public String getQuizTitle() {
		return quizTitle;
	}
	public void setQuizTitle(String title) {
		this.quizTitle = title;
	}
	
	@NotEmpty(message = "Quiz description must not be blank.")
	@Size(min = 2, max = 100)
	@Column(name = "DESCRIPTION", length = 500)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	//@NotEmpty(message = "Category must not be blank.")
	@Column(name = "CATEGORY")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "IS_RANDOM")
	public boolean isRandom() {
		return random;
	}
	public void setRandom(boolean random) {
		this.random = random;
	}

	@Column(name = "IS_MULTIPAGE")
	public boolean isMultipage() {
		return multipage;
	}

	public void setMultipage(boolean multipage) {
		this.multipage = multipage;
	}

	@Column(name = "IS_IMMEDIATE_CORRECTION")
	public boolean isImmediateCorrection() {
		return immediateCorrection;
	}

	public void setImmediateCorrection(boolean immediateCorrection) {
		this.immediateCorrection = immediateCorrection;
	}

	@Column(name = "IS_PRACTICE_MODE")
	public boolean isPracticeMode() {
		return practiceMode;
	}

	public void setPracticeMode(boolean practiceMode) {
		this.practiceMode = practiceMode;
	}

	
	@Column(name = "RATING")
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Column(name = "NUM_OF_QUESTIONS")
	public int getNumberOfQuestion() {
		return numberOfQuestion;
	}

	public void setNumberOfQuestion(int numberOfQuestion) {
		this.numberOfQuestion = numberOfQuestion;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false)
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="quiz", fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public void addQuestion(Question question) {
		questions.add(question);
		question.setQuiz(this);
	}
	
}
