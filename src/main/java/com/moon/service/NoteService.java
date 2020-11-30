package com.moon.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moon.dao.NoteDAO;
import com.moon.dto.UserLoginDTO;
import com.moon.entity.Note;
import com.moon.entity.User;
import com.moon.security.LoginFilter;

@Service
@Transactional // method and class level
public class NoteService {

	@Autowired
	private NoteDAO noteDao;
	
	@Autowired
	private UserService userService;

	public Long createNote(Note note, HttpServletRequest req) {

		note.setUserId(LoginFilter.user.getId());
		return noteDao.insert(note);
	}

	public ArrayList<Note> getAllNotes() {

		return (ArrayList<Note>) noteDao.getAllNotes();

	}

	public ArrayList<Note> getAllForUser(Long userId) {

		return (ArrayList<Note>) noteDao.getAllForUser(userId);
	}

	public Note getNoteFindByNoteId(Long noteId) {

		return noteDao.getFindByNoteId(noteId);

	}

	public Long updateNote(Note note, HttpServletRequest req) {

		noteDao.update(note);
		return 1l;
	}

	public Long deleteNote(Note note, HttpServletRequest req) {

		noteDao.delete(note);
		return 1l;
	}

	public ArrayList<Note> getAllForUser(UserLoginDTO  userLoginDto) {
		User userModel = new User();
		userModel.setUsername(userLoginDto.getUsername());
		userModel.setPassword(userLoginDto.getPassword());
		User user = userService.getFindByUsernameAndPassword(userModel);
		
		return (ArrayList<Note>) noteDao.getAllForUser(user.getId());
	}

}
