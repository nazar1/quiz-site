package com.nazarmerza.quiz.web.user;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nazarmerza.quiz.domain.Quiz;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.service.QuizService;
import com.nazarmerza.quiz.service.UserService;
import com.nazarmerza.quiz.util.Questions;


@Controller
public class AddQuizQuestions {

	private Validator validator;
	private UserService userService;
	private QuizService quizService;
	
	@Autowired
	public AddQuizQuestions(Validator validator, UserService userService,
			QuizService quizService) {
		this.validator = validator;
		this.userService = userService;
		this.quizService = quizService;
	}


	@RequestMapping(value = "/user/addQuestions", method = RequestMethod.GET)
	public String seupForm(Model model){
		model.addAttribute("questions", new Questions());
		return "/user/addQuestions";
	}
	
	@RequestMapping(value = "/user/addQuestions", method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("questions") Questions questions,
			BindingResult result, Model model, HttpSession session) {
		
		
		validator.validate(questions, result);
		
		if (result.hasErrors()) {
			return "/user/addQuestions";
		}
		
		// retrieve quiz from session
		Quiz quiz = (Quiz) session.getAttribute("quiz");
		quiz.addQuestion(questions.getQuestionQuery());
		quiz.addQuestion(questions.getQuestionFillInBlank());
		quiz.setNumberOfQuestion(2);
		quiz.setRating(1);
		quizService.createQuiz(quiz);
		
		User user = (User) session.getAttribute("user");
		userService.save(user);
		
		return "redirect:/user/user";
	}
}
