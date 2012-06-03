package com.nazarmerza.quiz.web.user.quiz;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
//import org.springframework.validation.Validator;
//import javax.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nazarmerza.quiz.domain.Question;
import com.nazarmerza.quiz.domain.QuestionQuery;
import com.nazarmerza.quiz.domain.QuestionFillInBlank;
import com.nazarmerza.quiz.domain.Quiz;
import com.nazarmerza.quiz.domain.QuizHistory;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.domain.types.QuestionType;
import com.nazarmerza.quiz.domain.validation.Answer;
import com.nazarmerza.quiz.service.QuizHistoryService;
import com.nazarmerza.quiz.service.QuizService;
import com.nazarmerza.quiz.service.UserService;
import com.nazarmerza.quiz.util.Questions;
import javax.validation.Validator;

@Controller
@RequestMapping("/user/quiz")
@SessionAttributes({"quiz", "questions"})
public class TakeQuizController {
	
	Long completionTime;
	Long startTime;

	private UserService userService;
	private QuizService quizService;
	private QuizHistoryService quizHistoryServcie;
	private Validator validator;

	//private Quiz currentQuiz;
	//private Questions currentQuestions;

	@Autowired
	public TakeQuizController(UserService userService, QuizService quizService,
			QuizHistoryService quizHistoryServcie, Validator validator) {
		this.userService = userService;
		this.quizService = quizService;
		this.quizHistoryServcie = quizHistoryServcie;
		this.validator = validator;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String setupForm(@PathVariable String id, Model model,
			HttpSession session) {

		Quiz quiz = quizService.findQuiz(new Long(id));

		// Get a form backing object where answer are set to empty string;
		Questions questions= new Questions(quiz.getQuestions());
		model.addAttribute("quiz", quiz);
		model.addAttribute("questions", questions);
		
		Long startTime = System.currentTimeMillis();
		session.setAttribute("startTime", startTime);

		return "/user/quiz";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String processSubmit(
			@ModelAttribute("questions") Questions questions,
			BindingResult result, Model model, HttpSession session) {

		completionTime = System.currentTimeMillis();
		startTime = (Long) session.getAttribute("startTime");
		
		this.validate(questions, result);
		if (result.hasErrors()) {
			return "/user/quiz";
		}
		
		
		QuizHistory quizHistory = new QuizHistory(
				(Quiz) session.getAttribute("quiz"),
				(User) session.getAttribute("user"),
				2, 
				completionTime - startTime);
		
		quizHistoryServcie.save(quizHistory);
		
		return "redirect:/user/user";
	}

	private void validate(Questions questions, BindingResult result) {
		Set<ConstraintViolation<Questions>> violations = validator.validate(
				questions, Answer.class);

		for (ConstraintViolation<Questions> violation : violations) {
			if (violation.getPropertyPath().toString().contains("answer")) {
				result.rejectValue(violation.getPropertyPath().toString(),
						null, violation.getMessage());
			}
		}

	}
	
}
