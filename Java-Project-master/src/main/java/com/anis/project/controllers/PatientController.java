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
import com.anis.project.services.PatientService;

@RestController
@RequestMapping("/api") 
public class PatientController {

	@Autowired
	private PatientService patientService;

	// Create One Patient
	@PostMapping(value = "/patients")
	public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
		Patient patientCreated = patientService.createPatient(patient);
		return new ResponseEntity<>(patientCreated, HttpStatus.CREATED);
	}

	// Get All Patients
	@GetMapping("/patients")
	public ResponseEntity<List<Patient>> getAllPatients() {
		List<Patient> allPatients = patientService.allPatients();
		return new ResponseEntity<>(allPatients, HttpStatus.OK);
	}

	// Find One Patient
	@GetMapping("/patients/{id}")
	public ResponseEntity<Patient> getOnePatient(@PathVariable Long id) {
		Patient onePatient = patientService.findPatient(id);
		return new ResponseEntity<>(onePatient, HttpStatus.OK);
	}
	
	// Update One Patient
	@PutMapping("/patients/{id}")
	public ResponseEntity<Patient> updateOnePatient(@PathVariable Long id, @RequestBody Patient patient) {
		Patient existingPatient = patientService.findPatient(id);
		if (existingPatient != null) {
			existingPatient.setFirstName(patient.getFirstName());
			existingPatient.setLastName(patient.getLastName());
			Patient updatedPatient = patientService.updatePatient(patient);
			return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Delete a consultation by ID
	@DeleteMapping("/patients/{id}")
	public ResponseEntity<Patient> deletePatient(@PathVariable Long id) {
		patientService.deletePatient(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
