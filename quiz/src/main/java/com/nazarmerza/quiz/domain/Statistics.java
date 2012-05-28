package com.nazarmerza.quiz.domain;

public class Statistics {

	private Integer userCount;
	private Integer quizCount;
	private int activeAnnouncementsCount;
	
	public Statistics(Integer userNum, Integer quizNum, int activeAnnouncements) {
		this.userCount = userNum;
		this.quizCount = quizNum;
		this.activeAnnouncementsCount = activeAnnouncements;
	}

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

	public Integer getQuizCount() {
		return quizCount;
	}

	public void setQuizCount(Integer quizCount) {
		this.quizCount = quizCount;
	}

	public int getActiveAnnouncementsCount() {
		return activeAnnouncementsCount;
	}

	public void setActiveAnnouncementsCount(int activeAnnouncementsCount) {
		this.activeAnnouncementsCount = activeAnnouncementsCount;
	}
	
	
	
	
}
