package com.nazarmerza.quiz.web;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.SessionAttributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nazarmerza.quiz.domain.Message;
import com.nazarmerza.quiz.domain.Profile;
import com.nazarmerza.quiz.domain.Quiz;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.service.QuizService;
import com.nazarmerza.quiz.service.UserService;

@Controller
public class LoginController {
	
	
	private UserService userService;
	private QuizService quizService;
	
	@Autowired
	public LoginController(UserService userService, QuizService quizService) {
		this.userService = userService;
		this.quizService = quizService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {

		return "login";
	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {

		model.addAttribute("error", "true");
		return "login";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model, HttpSession session) {
		session.invalidate();
		return "login";

	}
	

}