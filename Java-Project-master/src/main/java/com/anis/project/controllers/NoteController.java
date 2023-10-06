package com.anis.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anis.project.models.Consultation;
import com.anis.project.models.Note;
import com.anis.project.services.ConsultationService;
import com.anis.project.services.NoteService;

@RestController
@RequestMapping("/api") 
public class NoteController {

	@Autowired
	private NoteService noteService;
	@Autowired
	private ConsultationService consultationService;

	// Create One Note
	@PostMapping(value = "/notes/{consultationId}")
	public ResponseEntity<Note> createNote(
			@RequestBody Note note,
			@PathVariable("consultationId") Long consultationId) {
		Consultation consultation = consultationService.findOneById(consultationId);
		note.setConsultation(consultation);
		note.setNewNote(note.getNewNote());
		System.out.println(note.getNewNote());

		Note noteCreated = noteService.createNote(note);
		
		return new ResponseEntity<>(noteCreated, HttpStatus.CREATED);
	}

	// Get All Notes
	@GetMapping("/notes")
	public ResponseEntity<List<Note>> getAllNotes() {
		List<Note> allNotes = noteService.allNotes();
		return new ResponseEntity<>(allNotes, HttpStatus.OK);
	}

	// Find One Note
	@GetMapping("/notes/{id}")
	public ResponseEntity<Note> getOneNote(@PathVariable Long id) {
		Note oneNote = noteService.findNote(id);
		return new ResponseEntity<>(oneNote, HttpStatus.OK);
	}
	
	// Update One Note
	@PutMapping("/notes/{id}")
	public ResponseEntity<Note> updateOneNote(@PathVariable Long id, @RequestBody Note note) {
		Note existingNote = noteService.findNote(id);
		if (existingNote != null) {
			existingNote.setNewNote(note.getNewNote());
			Note updatedNote = noteService.updateNote(note);
			return new ResponseEntity<>(updatedNote, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Delete a consultation by ID
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<Note> deleteNote(@PathVariable Long id) {
		noteService.deleteNote(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
