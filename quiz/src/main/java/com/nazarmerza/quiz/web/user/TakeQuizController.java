package com.nazarmerza.quiz.web.user;

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
import com.nazarmerza.quiz.service.QuizService;
import com.nazarmerza.quiz.service.UserService;
import com.nazarmerza.quiz.util.Questions;
import javax.validation.Validator;

@Controller
@RequestMapping("/user/takeQuiz")
@SessionAttributes({"quiz", "questions"})
public class TakeQuizController {

	private UserService userService;
	private QuizService quizService;
	private Validator validator;

	//private Quiz currentQuiz;
	//private Questions currentQuestions;

	@Autowired
	public TakeQuizController(UserService userService, QuizService quizService,
			Validator validator) {
		this.userService = userService;
		this.quizService = quizService;
		this.validator = validator;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String setupForm(@PathVariable String id, Model model,
			HttpSession session) {

		Quiz quiz = quizService.getQuiz(new Long(id));

		// Get a form backing object where answer are set to empty string;
		Questions questions= new Questions(quiz.getQuestions());
		model.addAttribute("quiz", quiz);
		model.addAttribute("questions", questions);
		
		Long startTime = System.currentTimeMillis();
		session.setAttribute("startTime", startTime);

		return "/user/takeQuiz";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String processSubmit(
			@ModelAttribute("questions") Questions questions,
			BindingResult result, Model model, HttpSession session) {

		Long completionTime = System.currentTimeMillis();
		Long startTime = (Long) session.getAttribute("startTime");
		
		this.validate(questions, result);
		if (result.hasErrors()) {
			return "/user/takeQuiz";
		}
		
		
		List<Question> answredQuestion = new ArrayList<Question>();
		answredQuestion.add(questions.getQuestionQuery());
		answredQuestion.add(questions.getQuestionFillInBlank());
		
		quizService.recordQuizHistory((Quiz) session.getAttribute("quiz"), 
				answredQuestion, 
				completionTime - startTime,
				(User) session.getAttribute("user"));
		
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
