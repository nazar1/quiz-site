package com.nazarmerza.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nazarmerza.quiz.dao.AnnouncementDao;
import com.nazarmerza.quiz.dao.MessageDao;
import com.nazarmerza.quiz.dao.QuizDao;
import com.nazarmerza.quiz.dao.UserDao;
import com.nazarmerza.quiz.domain.Announcement;
import com.nazarmerza.quiz.domain.Quiz;
import com.nazarmerza.quiz.domain.Statistics;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.domain.types.Status;

@Repository
public class AdminServiceImpl implements AdminService{

	private UserDao userDao;
	private QuizDao quizDao;
	private MessageDao messageDao;
	private AnnouncementDao announcementDao;
	
	
	@Autowired
	public AdminServiceImpl(UserDao userDao, QuizDao quizDao,
			MessageDao messageDao, AnnouncementDao announcementDao) {
		this.userDao = userDao;
		this.quizDao = quizDao;
		this.messageDao = messageDao;
		this.announcementDao = announcementDao;
	}

	@Transactional
	public void createAnnouncement(Announcement announcement) {	
		announcementDao.save(announcement);
	}
	

	
	@Transactional
	public void deactivateAnnouncement(Long id){
		Announcement announcement = announcementDao.findById(id);
		announcement.setStatus(Status.INACTIVE);
		announcementDao.save(announcement);
		
	}
	
	@Transactional
	public void deleteAnnouncement(Long id){
		announcementDao.delete(id);
	}

	@Transactional
	public void removeUserAccount(Long id) {
		if (userDao.findById(id).getAuthority().equals("ROLE_USER")){
			userDao.delete(id);
		}
	}

	@Transactional
	public void promoteUserStatus(Long id) {
		User user = userDao.findById(id);
		
		if (user.getAuthority().equals("ROLE_USER")){
			user.setAuthority("ROLE_ADMIN");
			userDao.save(user);
		}
	}

	@Transactional
	public void removeQuiz(Quiz quiz) {
		
	}

	@Transactional
	public void removeQuizHistory(Quiz quiz) {
		
	}

	@Transactional
	public Statistics getStatistics() {
		int userCount = userDao.getDistinctCount();
		int quizCount = quizDao.getDistinctCount();
		int activeAnnouncementCount = announcementDao.getActiveAnnouncementsCount();
		
		return new Statistics(userCount, quizCount, activeAnnouncementCount);
	}
	
	

}
