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

import com.anis.project.models.Doctor;
import com.anis.project.models.Patient;
import com.anis.project.models.Review;
import com.anis.project.services.DoctorService;
import com.anis.project.services.PatientService;
import com.anis.project.services.ReviewService;

@RestController
@RequestMapping("/api") 
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private PatientService patientService;
	
	
	// Create One Review
	@PostMapping(value = "/reviews/{doctorId}/{patientId}")
	public ResponseEntity<Review> createReview(@RequestBody Review review,
			@PathVariable("doctorId") Long doctorId,
			@PathVariable("patientId") Long patientId) {
		Doctor doctor = doctorService.findDoctor((long) doctorId);
		Patient patient = patientService.findPatient((long) patientId);
		review.setDoctor(doctor);
		review.setPatient(patient);
		Review reviewCreated = reviewService.createReview(review);
		return new ResponseEntity<>(reviewCreated, HttpStatus.CREATED);
	}


	// Get All Reviews
	@GetMapping("/reviews")
	public ResponseEntity<List<Review>> getAllReviews() {
		List<Review> allReviews = reviewService.allReviews();
		return new ResponseEntity<>(allReviews, HttpStatus.OK);
	}

	// Find One Review
	@GetMapping("/reviews/{id}")
	public ResponseEntity<Review> getOneReview(@PathVariable Long id) {
		Review oneReview = reviewService.findReview(id);
		return new ResponseEntity<>(oneReview, HttpStatus.OK);
	}
	
	// Update One Review
	@PutMapping("/reviews/{id}")
	public ResponseEntity<Review> updateOneReview(@PathVariable Long id, @RequestBody Review review) {
		Review existingReview = reviewService.findReview(id);
		if (existingReview != null) {
			existingReview.setComment(review.getComment());
			existingReview.setRate(review.getRate());
			Review updatedReview = reviewService.updateReview(review);
			return new ResponseEntity<>(updatedReview, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Delete a review by ID
	@DeleteMapping("/reviews/{id}")
	public ResponseEntity<Review> deleteReview(@PathVariable Long id) {
		reviewService.deleteReview(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
