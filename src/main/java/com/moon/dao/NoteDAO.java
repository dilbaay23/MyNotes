package com.moon.dao;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moon.entity.Note;

@Repository
public class NoteDAO {

	@Autowired
	private SessionFactory sessionFactory;

	// CRUD

	// create
	public Long insert(Note note) {
		return (Long) sessionFactory.getCurrentSession().save(note);
	}

	public void update(Note note) {
		sessionFactory.getCurrentSession().update(note);
	}

	// if note has an id then this method updates the note which has this id. else
	// it creates a new note.
	public void persist(Note note) {
		sessionFactory.getCurrentSession().persist(note);
	}

	public void delete(Note note) {
		sessionFactory.getCurrentSession().delete(note);
	}

	public ArrayList<Note> getAllNotes() {
		Query query = sessionFactory.getCurrentSession().createQuery("FROM Note"); // Note is name of the class. it is
																					// not table's name
		return (ArrayList<Note>) query.getResultList();

	}

	public ArrayList<Note> getAllForUser(Long userId) {
		Query query = sessionFactory.getCurrentSession().createQuery("FROM Note WHERE userId=:userId")
				.setLong("userId",userId);
		return (ArrayList<Note>) query.getResultList();
	}
	
	public Note getFindByNoteId(Long noteId) {
		Query query = sessionFactory.getCurrentSession().createQuery("FROM Note WHERE id=:noteId order by id desc")
				.setLong("noteId",noteId);
		return  (Note) query.getSingleResult();
																	
	}

}
