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

import com.anis.project.models.Patient;
import com.anis.project.models.PatientInformation;
import com.anis.project.services.PatientInformationService;
import com.anis.project.services.PatientService;

@RestController
@RequestMapping("/api") 
public class PatientInformationController {

	@Autowired
	private PatientInformationService patientInformationService;
	@Autowired
	private PatientService patientService;

	// Create One PatientInformation
	@PostMapping(value = "/patientInformations/{patientId}")
	public ResponseEntity<PatientInformation> createPatientInformation(
			@RequestBody PatientInformation patientInformation,
			@PathVariable("patientId") Long PatientId ) {
		Patient thisPatient = patientService.findPatient((Long)PatientId);
		patientInformation.setPatient(thisPatient);
		PatientInformation patientInformationCreated = patientInformationService.createPatientInformation(patientInformation);
		System.out.println(thisPatient);
		return new ResponseEntity<>(patientInformationCreated, HttpStatus.CREATED);
	}

	// Get All PatientInformations
	@GetMapping("/patientInformations")
	public ResponseEntity<List<PatientInformation>> getAllPatientInformations() {
		List<PatientInformation> allPatientInformations = patientInformationService.allPatientInformations();
		return new ResponseEntity<>(allPatientInformations, HttpStatus.OK);
	}

	// Find One PatientInformation
	@GetMapping("/patientInformations/{id}")
	public ResponseEntity<PatientInformation> getOnePatientInformation(@PathVariable Long id) {
		PatientInformation onePatientInformation = patientInformationService.findPatientInformation(id);
		return new ResponseEntity<>(onePatientInformation, HttpStatus.OK);
	}
	
	// Update One PatientInformation
	@PutMapping("/patientInformations/{id}")
	public ResponseEntity<PatientInformation> updateOnePatientInformation(@PathVariable Long id, @RequestBody PatientInformation patientInformation) {
		PatientInformation existingPatientInformation = patientInformationService.findPatientInformation(id);
		if (existingPatientInformation != null) {
			existingPatientInformation.setAge(patientInformation.getAge());
			existingPatientInformation.setGender(patientInformation.getGender());
			existingPatientInformation.setPhoneNumber(patientInformation.getPhoneNumber());
			PatientInformation updatedPatientInformation = patientInformationService.updatePatientInformation(existingPatientInformation);
			return new ResponseEntity<>(updatedPatientInformation, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Delete a patientInformation by ID
	@DeleteMapping("/patientInformations/{id}")
	public ResponseEntity<PatientInformation> deletePatientInformation(@PathVariable Long id) {
		patientInformationService.deletePatientInformation(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
