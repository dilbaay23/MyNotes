package com.moon.mynotes;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.moon.entity.User;
import com.moon.service.UserService;

@Controller
public class RegisterController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginUser(@RequestParam(value = "status", required = false) String status, Model model) {

		if (status != null) {
			System.out.println(status);
			if (status.equals("ok")) {
				model.addAttribute("status", "Registration is Successful");
			} else {
				model.addAttribute("status", "Registration ERROR. Try Again!");
			}

		}
		return "login";

	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerUser( Model model) {

		return "register";
	}
	
	@RequestMapping(value = "/reg/{token}", method = RequestMethod.GET)
	public String registerIsOK(@PathVariable("token") String token,Model model) {

		if(userService.getFindByToken(token)) {
			return "redirect:/login?status=ok";
		}
		return "redirect:/login?status=error";
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestBody User user, HttpServletRequest req) { // With --HttpServletRequest  req-- we can get logging
			
		int status=controlUserPasswords(user);
		if(status!=0) {
			return new ResponseEntity<String>(status+"", HttpStatus.CREATED);
		}
		if(userService.insertUser(user, req).equals(1l)){
			return new ResponseEntity<String>("OK", HttpStatus.CREATED);
		};
		return new ResponseEntity<String>("ERROR", HttpStatus.CREATED);
	}
	
	
	private int controlUserPasswords(User user) {
		if(!user.getPassword().equals(user.getPassword2())) {
			return 1;
		}
		return 0;
	}
	
	

}
