package com.nazarmerza.quiz.web.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nazarmerza.quiz.domain.Announcement;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.service.AdminService;
import com.nazarmerza.quiz.service.QuizService;
import com.nazarmerza.quiz.service.UserService;


@Controller
//@RequestMapping("/admin")
public class AdminHomeController {
	
	private AdminService adminService;
	private UserService userService;
	private QuizService quizService;
	private Validator validator;
	
	@Autowired
	public AdminHomeController(AdminService adminService, UserService userService,
			QuizService quizService, Validator validator) {
		
		this.adminService = adminService;
		this.userService = userService;
		this.quizService = quizService;
		this.validator = validator;
	}
	
	@RequestMapping(value="/admin/admin", method = RequestMethod.GET)
	public String adminHomePage(Model model) {
		model.addAttribute("announcements", userService.getActiveAnnouncements());
		model.addAttribute("statistics", adminService.getStatistics());
		model.addAttribute("recentUsers", userService.getRecentUsers(10));
		return "/admin/home";
	}
	
	
	
}
