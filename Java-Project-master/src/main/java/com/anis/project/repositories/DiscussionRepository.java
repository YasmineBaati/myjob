package com.anis.project.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.anis.project.models.Discussion;

public interface DiscussionRepository extends CrudRepository<Discussion, Long> {
	List <Discussion> findAll();


}
