package com.nazarmerza.quiz.web.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nazarmerza.quiz.service.AdminService;
import com.nazarmerza.quiz.service.QuizService;
import com.nazarmerza.quiz.service.UserService;


@Controller
public class AdminUserController {
	private AdminService adminService;
	private UserService userService;
	
	@Autowired
	public AdminUserController(AdminService adminService, UserService userService) {
		this.adminService = adminService;
		this.userService = userService;
	}
	
	@RequestMapping(value = "/admin/user/promote/{id}", method = RequestMethod.GET)
	public String deactivate(@PathVariable String id, Model model, HttpSession session) {
		adminService.promoteUserStatus(new Long(id));
		//model.addAttribute("message", "User promoted.");
		return "redirect:/admin/admin";
	}
	
	@RequestMapping(value = "/admin/user/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable String id, Model model, HttpSession session) {
		adminService.removeUserAccount(new Long(id));
		//model.addAttribute("message", "Announcement deleted.");
		return "redirect:/admin/admin";
	}
	
	

}
