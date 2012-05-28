package com.nazarmerza.quiz.util;

import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import com.nazarmerza.quiz.domain.Question;
import com.nazarmerza.quiz.domain.QuestionFillInBlank;
import com.nazarmerza.quiz.domain.QuestionQuery;
import com.nazarmerza.quiz.domain.types.QuestionType;

public class Questions {

	@Valid
	private QuestionQuery questionQuery= new QuestionQuery();
	
	@Valid
	private QuestionFillInBlank questionFillInBlank=  new QuestionFillInBlank();
	
	public Questions(){
		
	}
	
	public  Questions(List<Question> questions){
		
		Iterator<Question> iter = questions.iterator();
		
			while (iter.hasNext()){
				Question question = iter.next();
				
				switch (question.getType()) {
				
				case QUESTION_RESPONSE:
					questionQuery = (QuestionQuery) question;
					questionQuery.setAnswer("");
					break;
					
				case FILL_IN_THE_BLANK:
					questionFillInBlank = (QuestionFillInBlank) question;
					questionFillInBlank.setAnswer("");
					break;
				default:
					break;
				}
			}
	}

	public QuestionQuery getQuestionQuery() {
		return questionQuery;
	}

	public void setQuestionQuery(QuestionQuery questionQuery) {
		this.questionQuery = questionQuery;
	}

	public QuestionFillInBlank getQuestionFillInBlank() {
		return questionFillInBlank;
	}

	public void setQuestionFillInBlank(QuestionFillInBlank questionFillInBlank) {
		this.questionFillInBlank = questionFillInBlank;
	}
	
	
}
