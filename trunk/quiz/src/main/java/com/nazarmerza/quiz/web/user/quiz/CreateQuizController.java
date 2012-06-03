package com.nazarmerza.quiz.web.user.quiz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.lang.Class;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AutoPopulatingList.ElementFactory;
import org.springframework.util.AutoPopulatingList;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nazarmerza.quiz.dao.*;
import com.nazarmerza.quiz.domain.*;
import com.nazarmerza.quiz.domain.types.Category;
import com.nazarmerza.quiz.service.UserService;
import com.nazarmerza.quiz.util.QuestionFactory;
import com.nazarmerza.quiz.util.Questions;

@Controller
@SessionAttributes("quiz")
public class CreateQuizController {

	private UserService userService;
	private Validator validator;
	
	
	@Autowired
	public CreateQuizController(UserService userService, Validator validator) {
		this.userService = userService;
		this.validator = validator;
	}
	
	//@ModelAttribute("quiz")
	/*
	public Quiz createQuiz(Long userId){
		//User user = userDao.findByName(user.getUserName());
		Quiz quiz = new Quiz();
		quiz.setCreatorId(userId);
		return quiz;
	}
	*/


	@ModelAttribute("categories")
	public Category[] addCategory(){
		return Category.values();
		
	}
	@RequestMapping(value = "/user/createQuiz", method = RequestMethod.GET)
	public String setupeForm(Model model, HttpSession session) {	
		User user = (User) session.getAttribute("user");
		Quiz quiz = new Quiz();
		//quiz.setCreatorId(userService.getUser(user.getUserName()).getId());
		quiz.setCreator(user);
		model.addAttribute("quiz", quiz);		
		return "/user/createQuiz";
	}

	@RequestMapping(value = "/user/createQuiz", method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("quiz") Quiz quiz, BindingResult result, 		
			Model model) {
		
		validator.validate(quiz, result);
		if (result.hasErrors()) {
			return "/user/createQuiz";
		}
		return "redirect:/user/addQuestions";
		
	}

}
