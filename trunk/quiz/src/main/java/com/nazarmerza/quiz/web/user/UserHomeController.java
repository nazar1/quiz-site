package com.nazarmerza.quiz.web.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nazarmerza.quiz.domain.Message;
import com.nazarmerza.quiz.domain.Quiz;
import com.nazarmerza.quiz.domain.QuizHistory;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.service.QuizService;
import com.nazarmerza.quiz.service.UserService;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
//@SessionAttributes("friends")
public class UserHomeController {

	
	private UserService userService;
	private QuizService quizService;
	
	@Autowired
	public UserHomeController(UserService userService, QuizService quizService) {
		this.userService = userService;
		this.quizService = quizService;
	}
		

	
	@RequestMapping(value = "/user/user", method = RequestMethod.GET)
	public String user(Model model, HttpSession session) {
		addUserModels(model, session);
		addGeneralModels(model);
		return "/user/user";
	}
	
	public void addUserModels(Model model, HttpSession session){
		
		
		
		User user = getAuthenticatedUser();
		int friendRequests = userService.getFriendRequests(user).size();
		int challenges = userService.getChallenges(user).size();
		int notes = userService.getNotes(user).size();
		session.setAttribute("user", user);
		session.setAttribute("friendRequests", friendRequests);
		session.setAttribute("challenges", challenges);
		session.setAttribute("notes", notes);
		
		model.addAttribute("friendsNames", userService.getUserFriendsNames(user.getId()));
		
		model.addAttribute("announcements", userService.getActiveAnnouncements());
		
		List<QuizHistory> userQuizHistory = quizService.getRecentQuizTakingActivities(user);
		model.addAttribute("userQuizHistory", userQuizHistory);
		
		List<Quiz> recentUserCreatedQuizes = quizService.getRecentlyCreatedQuizes(user);
		model.addAttribute("recentUserCreatedQuizes", recentUserCreatedQuizes);
		
		
	}
	
	public void addGeneralModels(Model model){
		
		List<Quiz> popularQuizes = quizService.getPopularQuizes();
		model.addAttribute("popularQuizes", popularQuizes);
		
		List<Quiz> createdQuizes = quizService.getRecentlyCreatedQuizes();
		model.addAttribute("createdQuizes", createdQuizes);
		
		List<Quiz> recentCreatedQuizes = quizService.getRecentlyCreatedQuizes();
		model.addAttribute("recentCreatedQuizes", recentCreatedQuizes);

	}
	
	public User getAuthenticatedUser() {
		// get authenticated user from security context
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		
		// get full user info and add
		User user = userService.getUser(userDetails.getUsername());
		return user;
	}

}
