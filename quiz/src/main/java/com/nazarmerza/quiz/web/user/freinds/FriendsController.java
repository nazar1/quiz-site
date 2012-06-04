package com.nazarmerza.quiz.web.user.freinds;

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
public class FriendsController {
	
	private UserService userService;
	
	@Autowired
	public FriendsController(UserService userService){
		this.userService = userService;
	}

	// Get the list of friends
	@RequestMapping(value="/friendsList", method = RequestMethod.GET)
	public String friendsList(Model model, HttpSession session){
		User user = (User) session.getAttribute("user");
		List<User> friends = userService.getUserFreinds(user);
		model.addAttribute("friends", friends);
		return "/user/friendsList";
	}
	
	
	@RequestMapping(value="/deleteFriend/{friendId}", method = RequestMethod.GET)
	public String sendNoteSetupForm(@PathVariable String friendId, 
			Model model, HttpSession session) {
		
		User user = (User) session.getAttribute("user");
		User friend = userService.findUser(new Long(friendId));
		
		userService.removeUserFriend(user, friend);
		model.addAttribute("message", "Friend has been deleted.");
		return "/user/friendsList";
	}
	
}
