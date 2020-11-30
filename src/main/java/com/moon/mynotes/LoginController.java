package com.moon.mynotes;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.moon.entity.User;
import com.moon.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/controlUser", method = RequestMethod.POST)
	public ResponseEntity<String> controlUser(@RequestBody User user, HttpServletRequest req) { // With --HttpServletRequest  req-- we can get logging
			User userModel=userService.getFindByUsernameAndPassword(user);
	req.getSession().setAttribute("user", userModel);
		if( userModel!=null) {
			req.getSession().setAttribute("user", userModel);
			return new ResponseEntity<String>("OK", HttpStatus.OK);
		}else
		return new ResponseEntity<String>("ERROR", HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutUser( Model model, HttpServletRequest req) {

		req.getSession().setAttribute("user", null);
		return "redirect:/login";
	}
	
}