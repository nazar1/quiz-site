package com.nazarmerza.quiz.web.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nazarmerza.quiz.domain.Message;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.domain.types.MessageType;
import com.nazarmerza.quiz.domain.types.Status;
import com.nazarmerza.quiz.service.UserService;


@Controller
@RequestMapping("/user")
@SessionAttributes({"searchUser","searchMessage"})
public class SearchController {
	
	private UserService userService;
	
	@Autowired
	public SearchController(UserService userService){
		this.userService = userService;
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public String setupForm(Model model, HttpSession session) {
		model.addAttribute("searchUser", new User());
		return "/user/search";
	}
	
	
	@RequestMapping(value="/search", method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("searchUser") User user,
			BindingResult result, Model model) {
		
		String userName = user.getUserName();
		if (userName == null || userName.isEmpty()) {
			model.addAttribute("searchMessage", "No search string (User Name) was entered.");
			return "/user/search";
			
		} else {
			user = userService.findUser(userName);
		}
		if (user == null){
			model.addAttribute("searchMessage", "User, " + userName + " does not exits.");
			return "redirect:/user/search";
		}
		
		model.addAttribute("searchUser", user);
		return "redirect:/user/searchResult";
	}
	

	
	
}
