package com.moon.service;

import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.moon.dao.UserDAO;

import com.moon.entity.User;

@Service
@Transactional // method and class level
public class UserService {

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private MailService mailService;
	

	public Long insertUser(User user, HttpServletRequest req) {
		String uuid = UUID.randomUUID().toString();
		user.setToken(uuid);
		
		if(userDao.insert(user)>0) {
			mailService.registerMail(user.getEmail(), user.getToken());
		}

		return 1l;
	}

	
	public User getFindByUsernameAndPassword(User user){
		
		return userDao.getFindByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
	
	public User getFindByUsername(String username){
		
		return userDao.getFindByUsername(username);
	}

	public void updateUser(User user) {

		userDao.update(user);
		
	}
	public boolean getFindByToken(String token){
		User user= userDao.getFindByToken(token);
				        
		if(user!=null) {
			user.setActive(true);
			updateUser(user);
			return true;		
		}else
		return false;
	}

	

}
