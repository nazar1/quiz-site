package com.nazarmerza.quiz.web.user.freinds;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nazarmerza.quiz.domain.Message;
import com.nazarmerza.quiz.domain.Note;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.domain.types.MessageType;
import com.nazarmerza.quiz.service.MessageService;
import com.nazarmerza.quiz.service.UserService;

@Controller
@RequestMapping("/user")
public class SendNoteController {

private UserService userService;
private MessageService messageService;
	
	@Autowired
	public SendNoteController(UserService userService, MessageService messageService){
		this.userService = userService;
		this.messageService = messageService;
	}
	
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
			model.addAttribute("message", "You must write something.");
			return "/user/sendNote";
		}
		User sender = (User) session.getAttribute("user");
		User receiver = (User) session.getAttribute("currentFriend");
		
		Note message = new Note(sender, receiver, note);	
		messageService.sendNote(message);
		
		model.addAttribute("message", "Your note has been sent.");
		return "/user/friendsList";
	}
}

