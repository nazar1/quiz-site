package com.nazarmerza.quiz.web.user;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nazarmerza.quiz.domain.Message;
import com.nazarmerza.quiz.domain.Quiz;
import com.nazarmerza.quiz.domain.QuizHistory;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.service.ApplicationService;
import com.nazarmerza.quiz.service.MessageService;
import com.nazarmerza.quiz.service.QuizService;
import com.nazarmerza.quiz.service.UserService;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class UserHomeController {

	
	private UserService userService;
	private QuizService quizService;
	private ApplicationService applicationService;
	private MessageService messageService;
	
	@Autowired
	public UserHomeController(UserService userService, QuizService quizService,
			ApplicationService applicationService, MessageService messageService) {
		this.userService = userService;
		this.quizService = quizService;
		this.applicationService = applicationService;
		this.messageService = messageService;
	}
		
	@RequestMapping(value = "/user/user", method = RequestMethod.GET)
	public String user(Model model, HttpSession session) {
		
		addUserModels(model, session);
		addGeneralModels(session);
		return "/user/user";
	}
	
	public void addUserModels(Model model, HttpSession session){
		
		// get authenticated user
		User user = getAuthenticatedUser();
		session.setAttribute("user", user);
		
		// add site announcement to the model
		session.setAttribute("announcements", applicationService.getActiveAnnouncements());
		
		
		//model.addAttribute("userQuizHistory", 
		//		quizService.getRecentQuizTakingActivities(user));
	
		session.setAttribute("recentUserCreatedQuizes", 
				quizService.getRecentlyCreatedQuizes(user));
		
		
		// get messages for this user and add them to the session
		session.setAttribute("friendRequests", messageService.getFriendRequests(user));
		session.setAttribute("notes", messageService.getNotes(user));
		session.setAttribute("challenges", messageService.getChallenges(user));
		
		session.setAttribute("friendsNames", getUserFriendsNames(user));
				
	}
	
	public void addGeneralModels(HttpSession session){
		
		List<Quiz> popularQuizes = quizService.getPopularQuizes();
		session.setAttribute("popularQuizes", popularQuizes);
		
		List<Quiz> createdQuizes = quizService.getRecentlyCreatedQuizes();
		session.setAttribute("createdQuizes", createdQuizes);
		
		List<Quiz> recentCreatedQuizes = quizService.getRecentlyCreatedQuizes();
		session.setAttribute("recentCreatedQuizes", recentCreatedQuizes);

	}
	
	public User getAuthenticatedUser() {
		// get authenticated user from security context
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		
		// get full user info and add
		User user = userService.findUser(userDetails.getUsername());
		return user;
	}
	
	private List<String> getUserFriendsNames(User user){
		
		List<User> friends =  userService.getUserFreinds(user);
		if(friends == null) return null;
		List<String> friendsNames = new LinkedList<String>();
		for (User friend: friends) {
			friendsNames.add(friend.getUserName());
		}
		return friendsNames;
	}

}
