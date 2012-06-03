package com.nazarmerza.quiz.web;

//import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nazarmerza.quiz.domain.Profile;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.exception.AccountExistsException;
import com.nazarmerza.quiz.exception.UserNameExistsException;
import com.nazarmerza.quiz.service.UserService;

@Controller
@RequestMapping("/signup")
public class SignupController {
		
	private Validator validator;
	private UserService userService;
	
	@Autowired
	public SignupController(Validator validator, UserService userService) {
		this.validator = validator;
		this.userService = userService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String signUpForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);

		return "signup";
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("user") User user,
			BindingResult result, Model model) {
		
		user.setAuthority("ROLE_USER");
		validator.validate(user, result);
		
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			return "signup";
			
		} else {
			// create user account
			createUserAccount(user);
			
			// direct to user page
			return "redirect:/user/user";
		}
		
	}
	
	private void createUserAccount(User user) {	
			userService.save(user);
	}
}

	