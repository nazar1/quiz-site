package com.nazarmerza.quiz.web.user.freinds;

import javax.servlet.http.HttpSession;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nazarmerza.quiz.domain.FriendRequest;
import com.nazarmerza.quiz.domain.Message;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.domain.types.MessageType;
import com.nazarmerza.quiz.domain.types.Status;
import com.nazarmerza.quiz.service.MessageService;
import com.nazarmerza.quiz.service.UserService;


@Controller
@RequestMapping("/user")
@SessionAttributes({"searchUser","searchMessage"})
public class UserSearchController {
	
	private static final String NO_SEARCH_NAME = 
			"No search name was entered. Please enter a username.";
	private static final String USER_NAME_NOT_FOUND = 
			"The user name you entered does not extist.";
	
	private static final String USER_NAME_FOUND = 
			"The user you were searching for is found.";
	
	private static final String FRIEND_REQUEST_SENT = 
			"Your friend request has been sent..";
	
	
	private UserService userService;
	private MessageService messageService;
	
	@Autowired
	public UserSearchController(UserService userService, MessageService messageService){
		this.userService = userService;
		this.messageService = messageService;
	}
	

	@RequestMapping(value="/searchUsers", method = RequestMethod.GET)
	public String setupForm(Model model, HttpSession session) {
		model.addAttribute("searchUser", new User());
		
		return "/user/searchUsers";
	}
	
	
	@RequestMapping(value="/searchUsers", method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("searchUser") User user,
			BindingResult result, Model model, HttpSession session) {
		
		session.removeAttribute("friendRequestSent");
		
		String userName = user.getUserName();
		if (userName == null || userName.isEmpty()) {
			model.addAttribute("searchMessage", NO_SEARCH_NAME);
			return "/user/searchUsers";
			
		} else {
			user = userService.findUser(userName);
		}
		if (user == null){
			model.addAttribute("searchMessage", USER_NAME_NOT_FOUND + ": " + userName);
			return "redirect:/user/searchUsers";
		}
		
		model.addAttribute("searchUser", user);
		return "/user/searchUsers";
	}
	
	@RequestMapping(value="/sendFriendRequest", method = RequestMethod.GET)
	public String sendFriendRequest(Model model, HttpSession session) {
		
		User sender = (User) session.getAttribute("user");
		User receiver = (User) session.getAttribute("searchUser");
		
		FriendRequest friendRequest = new FriendRequest(sender, receiver);
		messageService.sendFriendRequest(friendRequest);
		session.setAttribute("friendRequestSent", FRIEND_REQUEST_SENT);		
		return "redirect:/user/searchUsers";
	}	
}
