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
import com.anis.project.models.DoctorInformation;
import com.anis.project.services.DoctorInformationService;
import com.anis.project.services.DoctorService;

@RestController
@RequestMapping("/api") 
public class DoctorInformationController {

	@Autowired
	private DoctorInformationService doctorInformationService;	
	@Autowired
	private DoctorService doctorService;


	// Create One DoctorInformation
	@PostMapping(value = "/doctorInformations/{doctorId}")
	public ResponseEntity<DoctorInformation> createDoctorInformation(
	        @RequestBody DoctorInformation doctorInformation,
	        @PathVariable("doctorId") Long doctorId) {
	    Doctor thisDoctor = doctorService.findDoctor((Long)doctorId); // Grabbing the Doctor
	    doctorInformation.setDoctor(thisDoctor);
	    DoctorInformation doctorInformationCreated = doctorInformationService.createDoctorInformation(doctorInformation);
	    return new ResponseEntity<>(doctorInformationCreated, HttpStatus.CREATED);
	}


	// Get All DoctorInformations
	@GetMapping("/doctorInformations")
	public ResponseEntity<List<DoctorInformation>> getAllDoctorInformations() {
		List<DoctorInformation> allDoctorInformations = doctorInformationService.allDoctorInformations();
		return new ResponseEntity<>(allDoctorInformations, HttpStatus.OK);
	}

	// Find One DoctorInformation
	@GetMapping("/doctorInformations/{id}")
	public ResponseEntity<DoctorInformation> getOneDoctorInformation(@PathVariable Long id) {
		DoctorInformation oneDoctorInformation = doctorInformationService.findDoctorInformation(id);
		return new ResponseEntity<>(oneDoctorInformation, HttpStatus.OK);
	}
	
	// Update One DoctorInformation
	@PutMapping("/doctorInformations/{id}")
	public ResponseEntity<DoctorInformation> updateOneDoctorInformation(
			@PathVariable Long id, 
			@RequestBody DoctorInformation doctorInformation) {
		DoctorInformation existingDoctorInformation = doctorInformationService.findDoctorInformation(id);
		if (existingDoctorInformation != null) {
			existingDoctorInformation.setPrice(doctorInformation.getPrice());
			existingDoctorInformation.setStartTime(doctorInformation.getStartTime());
			existingDoctorInformation.setEndTime(doctorInformation.getEndTime());
			DoctorInformation updatedDoctorInformation = doctorInformationService.updateDoctorInformation(existingDoctorInformation);
			return new ResponseEntity<>(updatedDoctorInformation, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Delete a doctorInformation by ID
	@DeleteMapping("/doctorInformations/{id}")
	public ResponseEntity<DoctorInformation> deleteDoctorInformation(@PathVariable Long id) {
		doctorInformationService.deleteDoctorInformation(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
