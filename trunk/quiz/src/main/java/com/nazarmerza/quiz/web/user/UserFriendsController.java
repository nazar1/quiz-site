package com.nazarmerza.quiz.web.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nazarmerza.quiz.domain.Message;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.domain.types.MessageType;
import com.nazarmerza.quiz.service.UserService;

@Controller
@RequestMapping("/user")
@SessionAttributes("currentFriend")
public class UserFriendsController {
	
private UserService userService;
	
	@Autowired
	public UserFriendsController(UserService userService){
		this.userService = userService;
	}

	// Get the list of friends
	@RequestMapping(value="/friendsList", method = RequestMethod.GET)
	public String friendsList(Model model, HttpSession session){
		User user = (User) session.getAttribute("user");
		List<User> friends = userService.getUserFreinds(user.getId());
		model.addAttribute("friends", friends);
		return "/user/friendsList";
	}
	
	// Delete a friend
	//public String deleteFriend(){}
	

	@RequestMapping(value="/sendNote/{friendId}", method = RequestMethod.GET)
	public String sendNoteSetupForm(@PathVariable String friendId, 
			Model model, HttpSession session) {
		
		User user = (User) session.getAttribute("user");
		User freind = userService.getUserFreind(user.getId(), new Long(friendId));
		session.setAttribute("currentFriend", freind);
		return "/user/sendNote";
	}
	
	@RequestMapping(value="/sendNote", method = RequestMethod.POST)
	public String sendNote(@RequestParam("note") String note,
			Model model, HttpSession session) {
		
		if (note.isEmpty()){
			model.addAttribute("messageSendResult", "You must write something.");
			return "/user/sendNote";
		}
		User sender = (User) session.getAttribute("user");
		User receiver = (User) session.getAttribute("currentFriend");
		
		Message message = new Message(
				sender, receiver, MessageType.NOTE);
		message.setMessage(note);
		
		userService.sendNote(message);
		
		model.addAttribute("messageSendResult", "Your note has been sent.");
		return "/user/searchResult";
	}
	
}
