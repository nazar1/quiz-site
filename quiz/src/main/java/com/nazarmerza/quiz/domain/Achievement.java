package com.nazarmerza.quiz.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Achievement {

	@Column(name = "TEST_SECON_LEVEL", length = 100, nullable = false)
	String levelTwoComponent;

	public String getTest_secon_level() {
		return levelTwoComponent;
	}

	public void setTest_secon_level(String test_secon_level) {
		this.levelTwoComponent = test_secon_level;
	}
	
	
}
