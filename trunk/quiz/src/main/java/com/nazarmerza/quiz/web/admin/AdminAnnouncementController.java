package com.nazarmerza.quiz.web.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nazarmerza.quiz.domain.Announcement;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.service.AdminService;
import com.nazarmerza.quiz.service.QuizService;
import com.nazarmerza.quiz.service.UserService;
import com.nazarmerza.quiz.util.Questions;


@Controller
//@RequestMapping("/admin/ammouncement")
@SessionAttributes("announcement")
public class AdminAnnouncementController {

	
	private AdminService adminService;
	private UserService userService;
	private QuizService quizService;
	private Validator validator;
	
	@Autowired
	public AdminAnnouncementController(AdminService adminService, UserService userService,
			QuizService quizService, Validator validator) {
		
		this.adminService = adminService;
		this.userService = userService;
		this.quizService = quizService;
		this.validator = validator;
	}
	
	@RequestMapping(value="/admin/createAnnouncement", method = RequestMethod.GET)
	public String setupAnnouncementForm(Model model) {
		
		Announcement announcement = new Announcement();
		model.addAttribute("announcement", announcement);
		
		return "/admin/createAnnouncement";
	}
	
	@RequestMapping(value="/admin/createAnnouncement", method = RequestMethod.POST)
	public String createAnnouncement(@ModelAttribute("announcement")
	Announcement announcement,BindingResult result, Model model, HttpSession session) {
		
		validator.validate(announcement, result);
		if (result.hasErrors()) {
			return "/admin/createAnnouncement";
		} 
		
		User user = (User) session.getAttribute("user");
		announcement.setCreator(user);
		adminService.createAnnouncement(announcement);
		
		model.addAttribute("message", "Announcement successfully created.");
		return "/admin/home";
	}
	
	
	@RequestMapping(value = "/admin/announcement/deactivate/{id}", method = RequestMethod.GET)
	public String deactivate(@PathVariable String id, Model model, HttpSession session) {
		adminService.deactivateAnnouncement(new Long(id));
		model.addAttribute("message", "Announcement deactivated.");
		return "redirect:/admin/admin";
	}
	
	@RequestMapping(value = "/admin/announcement/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable String id, Model model, HttpSession session) {
		adminService.deleteAnnouncement(new Long(id));
		model.addAttribute("message", "Announcement deleted.");
		return "redirect:/admin/admin";
	}
}
