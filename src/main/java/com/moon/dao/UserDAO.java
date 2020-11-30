package com.moon.dao;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moon.entity.Note;
import com.moon.entity.User;

@Repository
public class UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	// CRUD

	// create
	public Long insert(User user) {
		return (Long) sessionFactory.getCurrentSession().save(user);
	}

	public void update(User user) {
		sessionFactory.getCurrentSession().update(user);
	}


	
	//Read
		public User getFindByUsernameAndPassword(String username, String password){
			Query query = sessionFactory.getCurrentSession().createQuery("FROM User Where username =:username AND password =:password AND  active=:active")
					.setString("username", username)
			        .setString("password", password)
			        .setBoolean("active", true);
			User user=null;
			   
			try {
				user=(User) query.getSingleResult();
				
			} catch (javax.persistence.NoResultException e) {
				user=null;
			}
			 return user;
		}
		
		public User getFindByUsername(String username){
			Query query = sessionFactory.getCurrentSession().createQuery("FROM User Where username =:username")
					.setString("username", username);
			        
			return (User) query.getSingleResult();
		}


		public User getFindByToken(String token){
			Query query = sessionFactory.getCurrentSession().createQuery("FROM User Where token =:token")
					.setString("token", token);
			User user=null;
			   
			try {
				user=(User) query.getSingleResult();
				
			} catch (javax.persistence.NoResultException e) {
				user=null;
			}
			 return user;
		}
		
	

	




}
