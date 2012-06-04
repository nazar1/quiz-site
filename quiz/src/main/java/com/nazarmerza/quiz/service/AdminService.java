package com.nazarmerza.quiz.service;

import java.util.List;

import com.nazarmerza.quiz.domain.Announcement;
import com.nazarmerza.quiz.domain.Quiz;
import com.nazarmerza.quiz.domain.Statistics;
import com.nazarmerza.quiz.domain.User;

public interface AdminService {

	/* announcement methods */
	public void createAnnouncement(Announcement announcement);
	
	public void deactivateAnnouncement(Long id);
	public void deleteAnnouncement(Long id);
	
	public List<User> getRecentUsers(int maxResults);
	
	public void removeUserAccount(Long id);
	public void promoteUserStatus(Long id);
	//public void removeQuiz(Quiz quiz);
	//public void removeQuizHistory(Quiz quiz);
	
	/* site statistics */
	public Statistics getStatistics();
}
