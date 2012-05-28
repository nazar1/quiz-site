package com.nazarmerza.quiz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nazarmerza.quiz.dao.QuestionDao;
import com.nazarmerza.quiz.dao.QuizDao;
import com.nazarmerza.quiz.dao.QuizHistoryDao;
import com.nazarmerza.quiz.dao.UserDao;
import com.nazarmerza.quiz.domain.Question;
import com.nazarmerza.quiz.domain.QuestionFillInBlank;
import com.nazarmerza.quiz.domain.QuestionQuery;
import com.nazarmerza.quiz.domain.Quiz;
import com.nazarmerza.quiz.domain.QuizHistory;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.domain.Achievement;
import com.nazarmerza.quiz.domain.Profile;
import com.nazarmerza.quiz.domain.types.Category;
import com.nazarmerza.quiz.domain.types.QuestionType;

@Controller
public class TestController {

	UserDao userDao;
	QuizDao quizDao;
	QuestionDao questionDao;
	QuizHistoryDao quizHistoryDao;
	
	public TestController(){
		
	}
	@Autowired
	public TestController(UserDao userDao, QuizDao quizDao,
			QuestionDao questionDao, QuizHistoryDao quizHistoryDao) {
		this.userDao = userDao;
		this.quizDao = quizDao;
		this.questionDao = questionDao;
		this.quizHistoryDao = quizHistoryDao;
	}

	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test(Model model) {
		System.out.println("before");
		testQuizHistory();
		return "/user/test";
	}
	
	private void testQuizHistory(){
		QuizHistory quizHistory = new QuizHistory();
		quizHistoryDao.save(quizHistory);
	}
	
	@Transactional
	public void TestMapping() {
		/* Test quiz creation */
		System.out.println("before");
		User user = userDao.findByName("ahmad");
		System.out.println("after");
		
		QuestionQuery question1 = new QuestionQuery();
		question1.setType(QuestionType.QUESTION_RESPONSE);
		question1.setQuestion("what is my name");
		question1.setAnswer("ahmad");
		//question.setOrder(1);
		
		QuestionFillInBlank question2 = new QuestionFillInBlank();
		question2.setType(QuestionType.FILL_IN_THE_BLANK);
		question2.setPre("My name ");
		question2.setAnswer("is");
		question2.setPost("qodos");
		//question.setOrder(1);
		
		Quiz quiz = new Quiz();
		quiz.setCategory(Category.SCIENCE);
		quiz.setCreator(user);
		quiz.setDescription("test description");
		//quiz.setQuestion(question);

		question1.setQuiz(quiz);
		question2.setQuiz(quiz);
		
		quiz.addQuestion(question1);
		quiz.addQuestion(question2);
		
		quizDao.save(quiz);
		//questionDao.save(question1);
		//questionDao.save(question2);
		
	}
}
