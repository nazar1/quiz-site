package com.nazarmerza.quiz.util;
import org.springframework.util.AutoPopulatingList.ElementFactory;
import org.springframework.util.AutoPopulatingList.ElementInstantiationException;

import com.nazarmerza.quiz.domain.Question;
import com.nazarmerza.quiz.domain.QuestionFillInBlank;
import com.nazarmerza.quiz.domain.QuestionQuery;
import com.nazarmerza.quiz.domain.Quiz;
import com.nazarmerza.quiz.domain.types.Category;
import com.nazarmerza.quiz.domain.types.QuestionType;

public class QuestionFactory implements ElementFactory{

	private Quiz quiz;
	
	public QuestionFactory(Quiz quiz){
		this.quiz = quiz;
		System.out.println("inside constructor.");
	}
	public Object createElement(int index) 
			throws ElementInstantiationException {	
			QuestionQuery questionQuery = new QuestionQuery();
			//quiz.addQuestion1(questionQuery);
			return questionQuery;
	
	}

}
