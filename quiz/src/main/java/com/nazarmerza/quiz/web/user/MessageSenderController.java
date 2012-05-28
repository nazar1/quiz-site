package com.nazarmerza.quiz.web.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nazarmerza.quiz.domain.Message;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.domain.types.MessageType;
import com.nazarmerza.quiz.service.UserService;


@Controller
@RequestMapping("/user")
public class MessageSenderController {
	
	
	private UserService userService;
	
	@Autowired
	public MessageSenderController(UserService userService){
		this.userService = userService;
	}
	
	@RequestMapping(value="/searchResult", method = RequestMethod.GET)
	public String searchResult(Model model, HttpSession session) {
		return "/user/searchResult";
	}
	
	@RequestMapping(value="/sendFriendRequest", method = RequestMethod.GET)
	public String sendFriendRequest(Model model, HttpSession session) {
		
		User sender = (User) session.getAttribute("user");
		User receiver = (User) session.getAttribute("searchUser");
		
		Message friendRequest = new Message(
				sender, receiver, MessageType.FRIEND_REQUEST);
		userService.sendFriendRequest(friendRequest);
		model.addAttribute("messageSendResult", "Your friend request has been sent.");
		return "/user/searchResult";
	}	
	
	
}
