package com.anis.project.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.anis.project.models.Review;

public interface ReviewRepository extends CrudRepository<Review, Long> {
	List <Review> findAll();
}
