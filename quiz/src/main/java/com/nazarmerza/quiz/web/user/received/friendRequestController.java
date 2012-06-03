package com.nazarmerza.quiz.web.user.received;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nazarmerza.quiz.domain.FriendRequest;
import com.nazarmerza.quiz.domain.Message;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.service.MessageService;
import com.nazarmerza.quiz.service.UserService;

@Controller
@RequestMapping(value="/user")
//@SessionAttributes("friendRequests")
public class friendRequestController {

	private UserService userService;
	private MessageService messageService;
	private User user;
	
	@Autowired
	public friendRequestController(UserService userService, MessageService messageService){
		this.userService = userService;
		this.messageService = messageService;
	}
	
	@RequestMapping(value="/friendRequests", method = RequestMethod.GET)
	public String displayFriendRequests(Model model, HttpSession session) {
	
		this.user = (User) session.getAttribute("user");
		session.setAttribute("friendRequests", messageService.getFriendRequests(user));
		return "/user/friendRequests";
	}
	
	@RequestMapping(value="/friendRequest/deny/{id}", method = RequestMethod.GET)
	public String denyFriendRequest(@PathVariable String id, 
			Model model, HttpSession session) {
		Message message = messageService.findMessage(new Long(id));
		messageService.delete(message);
		session.setAttribute("friendRequests", messageService.getFriendRequests(user));
		return "/user/friendRequests";	
	}
	
	@RequestMapping(value="/friendRequest/accept/{id}", method = RequestMethod.GET)
	public String acceptFriendRequest(@PathVariable String id, 
			Model model, HttpSession session) {
		
		String returnPage = "/user/friends/friendRequests";	
		Message message = messageService.findMessage(new Long(id));
		FriendRequest friendRequest;
		
		if(message instanceof FriendRequest){
			friendRequest = (FriendRequest) message;
		} else {
			messageService.delete(message);
			return returnPage;
		}
		
		messageService.approveFriendRequest(friendRequest);
		User user = (User) session.getAttribute("user");
		session.setAttribute("friendsNames", getUserFriendsNames(user));
		session.setAttribute("friendRequests", messageService.getFriendRequests(user));
		
		return "/user/friendRequests";
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
