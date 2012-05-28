package com.nazarmerza.quiz.domain.types;

public enum QuestionType {
	
		QUESTION_RESPONSE,
		FILL_IN_THE_BLANK,
		MULTIPLE_CHOICE,
		IMAGE_RESPONSE;
		
		
		public String getName(){
			return name();
		}
		
}
