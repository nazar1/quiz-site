package com.nazarmerza.quiz.web.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nazarmerza.quiz.domain.Message;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.service.UserService;

@Controller
@RequestMapping(value="/user")
@SessionAttributes("friendRequestMessages")
public class UserReceivedMessagesController {

private UserService userService;
	
	@Autowired
	public UserReceivedMessagesController(UserService userService){
		this.userService = userService;
	}
	
	@RequestMapping(value="/friendRequests", method = RequestMethod.GET)
	public String displayFriendRequests(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		addFriendRequestMessages(user, model);
		return "/user/friendRequestMessages";
	}
	
	@RequestMapping(value="/friendRequest/accept/{id}", method = RequestMethod.GET)
	public String acceptFriendRequest(@PathVariable String id, 
			Model model, HttpSession session) {
		
		Message message = userService.getMessage(new Long(id));
		userService.approveFriendRequest(message);
		
		User user = (User) session.getAttribute("user");
		addFriendRequestMessages(user, model);
		
		return "/user/friendRequestMessages";
		
	}
	
	private void addFriendRequestMessages(User user, Model model){
		List<Message> friendRequestMessages = userService.getFriendRequests(user);
		model.addAttribute("friendRequestMessages", friendRequestMessages);
	}
}
