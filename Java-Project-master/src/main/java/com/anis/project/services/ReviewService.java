package com.anis.project.services;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.anis.project.models.Review;
import com.anis.project.repositories.ReviewRepository;

@Service
public class ReviewService {
	private final ReviewRepository reviewRepository;

	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}
	
	// Display all Reviews
	public List<Review> allReviews() {
		return reviewRepository.findAll();
	}

	// Create a review
	public Review createReview(Review review) {
		return reviewRepository.save(review);
		
	}

	// Find one
	public Review findReview(Long id) {
		Optional<Review> optionalReview = reviewRepository.findById(id);
		if (optionalReview.isPresent()) {
			return optionalReview.get();
		} else {
			return null;
		}
	}

	
	// Delete a review
	public void deleteReview(Long id) {
		reviewRepository.deleteById(id);
	}

	// Update a review
	public Review updateReview(Review review) {
		return reviewRepository.save(review);
	}
}
