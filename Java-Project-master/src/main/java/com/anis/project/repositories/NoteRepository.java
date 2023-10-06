package com.anis.project.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.anis.project.models.Note;

public interface NoteRepository extends CrudRepository<Note, Long> {
	List <Note> findAll();
}	
