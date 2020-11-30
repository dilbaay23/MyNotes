package com.moon.endpoint;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moon.dto.UserLoginDTO;
import com.moon.entity.Note;
import com.moon.service.NoteService;

@RestController
@RequestMapping(value = "/rest")
public class NoteEndPoint {
	
	@Autowired
	private NoteService noteService;
	
	@RequestMapping(value="/getAll", method = RequestMethod.POST)
	public ResponseEntity<ArrayList<Note>> getMyNotes(@RequestBody UserLoginDTO userLoginDto){
		
		
		return new ResponseEntity<>(noteService.getAllForUser(userLoginDto),HttpStatus.OK);
	

	}

}
