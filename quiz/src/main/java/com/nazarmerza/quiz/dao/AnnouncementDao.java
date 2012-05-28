package com.nazarmerza.quiz.dao;

import java.util.List;

import com.nazarmerza.quiz.domain.Announcement;
import com.nazarmerza.quiz.domain.Quiz;

public interface AnnouncementDao extends GenericDao<Announcement, Long>{
	
	public List<Announcement> getActiveAnnouncements();
	public int getActiveAnnouncementsCount();
	//public void deactivateAnnouncement(Long id);

}
