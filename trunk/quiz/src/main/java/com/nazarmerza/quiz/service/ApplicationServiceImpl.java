package com.nazarmerza.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nazarmerza.quiz.dao.AnnouncementDao;
import com.nazarmerza.quiz.domain.Announcement;

@Repository
public class ApplicationServiceImpl implements ApplicationService{

	AnnouncementDao announcementDao;
	
	@Autowired
	public ApplicationServiceImpl(AnnouncementDao announcementDao){
		this.announcementDao = announcementDao;
	}
	
	@Transactional
	public List<Announcement> getActiveAnnouncements(){
		return  announcementDao.getActiveAnnouncements();
	}

}
