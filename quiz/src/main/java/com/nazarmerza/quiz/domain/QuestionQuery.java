package com.nazarmerza.quiz.domain;

import javax.persistence.Column;
import org.hibernate.validator.constraints.NotEmpty;

import com.nazarmerza.quiz.domain.types.QuestionType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@Table("question_query")
@DiscriminatorValue("QQ")
public class QuestionQuery extends Question {

	private String question;
	
	public QuestionQuery(){
		super.setType(QuestionType.QUESTION_RESPONSE);
	}
	
	@NotEmpty(message = "Question must not be empty")
	//@NotEmpty(message="{NotEmpty.question.question}")
	@Column(name = "QUESTION")
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}


	@Override
	public boolean validateResponse(String response) {
		if (response.equals(this.answer)){
			return true;
		}
		return false;
	}
	

}
