package com.moon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.moon.mynotes.HomeController;

@Service
public class MailService {

	@Autowired
	private MailSender mailSender;
	
	public void registerMail(String userMail, String token) {
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom("moonuserapi6@gamil.com");
		email.setTo(userMail);
		email.setSubject("Confirm Your Account");
		email.setText("Please confirm your account by clicking the link .\n\n" + HomeController.url + "/reg/" + token);
		
		mailSender.send(email);
		
		
	}
}