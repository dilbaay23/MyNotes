package com.moon.mynotes;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.moon.entity.Note;
import com.moon.security.LoginFilter;
import com.moon.service.MailService;
import com.moon.service.NoteService;

@Controller
public class HomeController {

	public static String url = "http://localhost:8080/mynotes";

	@Autowired
	private NoteService noteService;

	@Autowired
	private MailService mailService;

	@RequestMapping(value = { "", "/" }, method = { RequestMethod.GET })
	public String homeDirect(Model model, HttpServletRequest req) {

		//return "redirect:/index";     // after filter class, we can't use this redirect anymore
		return "login";
	}

	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public String home(Model model, HttpServletRequest req) {

	
		model.addAttribute("user", req.getSession().getAttribute("user"));
		model.addAttribute("title", "My Notes");
		model.addAttribute("notes", noteService.getAllForUser(1l)); // you can use this method with jstl codes in
																	// index.jsp... but ajax in js is beter and more
																	// simple for complex projects.

		return "index";
	}

	@RequestMapping(value = "/addNotePage", method = RequestMethod.GET)
	public String addNotePage(Model model,HttpServletRequest req) {

		model.addAttribute("user", req.getSession().getAttribute("user"));
		return "addNote";
	}

	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public String home(@PathVariable("id") Long id, Model model,HttpServletRequest req) {

		model.addAttribute("user", req.getSession().getAttribute("user"));
		model.addAttribute("id", id);

		return "details";
	}

	@RequestMapping(value = "/addNote", method = RequestMethod.POST)
	public ResponseEntity<String> addNote(@RequestBody Note note, HttpServletRequest req) { // With --HttpServletRequest
																							// req-- we can get logging
																							// info and user info etc...

		noteService.createNote(note, req);
		return new ResponseEntity<String>("OK", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/getNotes", method = RequestMethod.POST)
	public ResponseEntity<ArrayList<Note>> getNotes(HttpServletRequest req) {

		return new ResponseEntity<>(noteService.getAllForUser(LoginFilter.user.getId()), HttpStatus.CREATED);
	}



	@RequestMapping(value = "/getNote", method = RequestMethod.POST)
	public ResponseEntity<Note> getNote(@RequestBody Long noteId, HttpServletRequest req) {

		Note note = noteService.getNoteFindByNoteId(noteId);
		if (note.getUserId().equals(LoginFilter.user.getId())) {
			return new ResponseEntity<>(note, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}
	
	
	

	@RequestMapping(value = "/updateNote", method = RequestMethod.POST)
	public ResponseEntity<String> updtateNote(@RequestBody Note note, HttpServletRequest req) {

		Note oldNote = noteService.getNoteFindByNoteId(note.getId());
		oldNote.setTitle(note.getTitle());
		oldNote.setContent(note.getContent());

		noteService.updateNote(oldNote, req);
		return new ResponseEntity<String>("OK", HttpStatus.CREATED);
	}
	
	
	

	@RequestMapping(value = "/deleteNote", method = RequestMethod.POST)
	public ResponseEntity<String> deleteNote(@RequestBody Note note, HttpServletRequest req) {

		Note oldNote = noteService.getNoteFindByNoteId(note.getId());

		noteService.deleteNote(oldNote, req);
		return new ResponseEntity<String>("OK", HttpStatus.CREATED);
	}

}
