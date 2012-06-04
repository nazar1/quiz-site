package com.nazarmerza.quiz.web.user.received;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nazarmerza.quiz.domain.Message;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.service.MessageService;
import com.nazarmerza.quiz.service.UserService;


@Controller
@RequestMapping(value="/user")
public class NotesController {
	
	private UserService userService;
	private MessageService messageService;
	private User user;
	
	@Autowired
	public NotesController(UserService userService, MessageService messageService){
		this.userService = userService;
		this.messageService = messageService;
	}
	
	@RequestMapping(value="/notes", method = RequestMethod.GET)
	public String displayFriendRequests(Model model, HttpSession session) {
	
		this.user = (User) session.getAttribute("user");
		session.setAttribute("notes", messageService.getNotes(user));
		return "/user/notes";
	}
	
	@RequestMapping(value="/notes/delete/{id}", method = RequestMethod.GET)
	public String denyFriendRequest(@PathVariable String id, 
			Model model, HttpSession session) {
		Message message = messageService.findMessage(new Long(id));
		messageService.delete(message);
		session.setAttribute("notes", messageService.getNotes(user));
		return "/user/notes";	
	}

}
