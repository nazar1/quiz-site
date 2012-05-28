package com.nazarmerza.quiz.domain.types;

public enum UserRank {
	
	AMATEUR_AUTHOR,
	PROLIFIC_AUTHOR,
	PRODIGIOUS_AUTHOR,
	QUIZ_MACHINE,
	THE_GREATEST;
	
	public String getName(){
		return name();
	}

}
