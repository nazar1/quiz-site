package com.nazarmerza.quiz.service;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nazarmerza.quiz.dao.QuizDao;
import com.nazarmerza.quiz.dao.QuizHistoryDao;
import com.nazarmerza.quiz.dao.UserDao;
import com.nazarmerza.quiz.dao.hibernate.QuizHistoryDaoHibernate;
import com.nazarmerza.quiz.domain.Question;
import com.nazarmerza.quiz.domain.Quiz;
import com.nazarmerza.quiz.domain.QuizHistory;
import com.nazarmerza.quiz.domain.User;

@Repository
public class QuizServiceImpl implements QuizService{

	private QuizDao quizDao;
	private QuizHistoryDao quizHistoryDao;
	private int maxResult;
	
	@Autowired
	public QuizServiceImpl(QuizDao quizDao, QuizHistoryDao quizHistoryDao) {
		this.quizDao = quizDao;
		this.quizHistoryDao = quizHistoryDao;
		// set default number of max results
		this.maxResult = 5;
	}

	
	public int getMaxResults() {
		return maxResult;
	}

	public void setMaxResults(int maxResults) {
		this.maxResult = maxResults;
	}


	@Transactional
	public void createQuiz(Quiz quiz) {
		quizDao.save(quiz);
	}

	@Transactional
	public Quiz getQuiz(Long id) {
		return quizDao.findById(id);
	}

	@Transactional
	public List<Quiz> getPopularQuizes() {
		return quizDao.getPopularQuizes(maxResult);
	}

	@Transactional
	public List<Quiz> getRecentlyCreatedQuizes() {
		return quizDao.getRecentlyCreatedQuizes(maxResult);
	}
	
	@Transactional
	public List<Quiz> getRecentlyCreatedQuizes(User user) {
		return quizDao.getRecentlyCreatedQuizes(user, maxResult);
	}
	
	@Transactional
	public List<Quiz> getRecentlyTakenQuizes() {
		return quizDao.getRecentlyCreatedQuizes(maxResult);
	}
	
	@Transactional
	public List<User> getHighestPerformers() {
		// TODO Auto-generated method stub
		return null;
	}



	
	@Transactional
	public void recordQuizHistory(Quiz quiz, List<Question> answeredQuestions,
			Long completionTime, User user){
		int correctAnswers = 0;
		for(Question answeredQuestion: answeredQuestions){
			for(Question question: quiz.getQuestions()){
				if(question.validateResponse(answeredQuestion.getAnswer())){
					correctAnswers =+ 1;
				}
			}
		}
		double score = ((correctAnswers * 100.0) / quiz.getNumberOfQuestion());
		
		QuizHistory quizHistory = new QuizHistory();
		quizHistory.setQuiz(quiz);
		quizHistory.setUser(user);
		quizHistory.setCorrectAnswers(correctAnswers);
		quizHistory.setNumberOfQuestions(quiz.getNumberOfQuestion());
		quizHistory.setScore(score);
		quizHistory.setMiliseconds(completionTime);
		quizHistoryDao.save(quizHistory);
		
	}

	@Transactional
	public List<QuizHistory> getRecentQuizTakingActivities() {
		return quizHistoryDao.getRecentQuizTakingHistory(maxResult * 5);
	}

	@Transactional
	public List<QuizHistory> getRecentQuizTakingActivities(User user) {
		return quizHistoryDao.getRecentQuizTakingHistory(user, maxResult);
	}
	
}
