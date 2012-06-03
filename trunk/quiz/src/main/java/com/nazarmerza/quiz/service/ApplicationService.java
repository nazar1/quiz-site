package com.nazarmerza.quiz.service;

import java.util.List;

import com.nazarmerza.quiz.dao.AnnouncementDao;
import com.nazarmerza.quiz.domain.Announcement;
import com.nazarmerza.quiz.domain.User;

public interface ApplicationService {

	
	
	public List<Announcement> getActiveAnnouncements();
	
}
