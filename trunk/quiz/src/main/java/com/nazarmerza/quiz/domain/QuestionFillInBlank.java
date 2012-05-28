package com.nazarmerza.quiz.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.nazarmerza.quiz.domain.types.QuestionType;

@Entity
@DiscriminatorValue("FB")
public class QuestionFillInBlank extends Question{
	
	private String pre;
	private String post;
	
	public QuestionFillInBlank(){
		super.setType(QuestionType.FILL_IN_THE_BLANK);
	}

	@Column(name = "PRE")
	public String getPre() {
		return pre;
	}
	
	public void setPre(String pre) {
		this.pre = pre;
	}
	
	@Column(name = "POST")
	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}
	
	
	/* business methods */
	@Override
	public boolean validateResponse(String response) {
		if (response.equals(this.answer)){
			return true;
		}
		return false;
	}

}
