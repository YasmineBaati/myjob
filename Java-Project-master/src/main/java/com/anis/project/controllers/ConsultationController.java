package com.anis.project.controllers;

import java.util.List;
import java.util.Optional;

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
import com.anis.project.models.Doctor;
import com.anis.project.models.Patient;
import com.anis.project.services.ConsultationService;
import com.anis.project.services.DoctorService;
import com.anis.project.services.PatientService;

@RestController
@RequestMapping("/api") 
public class ConsultationController {

	@Autowired
	private ConsultationService consultationService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private PatientService patientService;

	// Create One Consultation
	@PostMapping(value = "/consultations/{doctorId}/{patientId}")
	public ResponseEntity<Consultation> createConsultation(@RequestBody Consultation consultation,
			@PathVariable("doctorId") Long doctorId,
			@PathVariable("patientId") Long patientId) {
		Doctor doctor = doctorService.findDoctor((long) doctorId);
		Patient patient = patientService.findPatient((long) patientId);
		consultation.setDoctor(doctor);
		consultation.setPatient(patient);
		Consultation consultationCreated = consultationService.create(consultation);
		return new ResponseEntity<>(consultationCreated, HttpStatus.CREATED);
	}

	// Get All Consultations
	@GetMapping("/consultations")
	public ResponseEntity<List<Consultation>> getAllConsultations() {
		List<Consultation> allConsultations = consultationService.findAllConsultation();
		return new ResponseEntity<>(allConsultations, HttpStatus.OK);
	}

	// Find One Consultation
	@GetMapping("/consultations/{id}")
	public ResponseEntity<Consultation> getOneConsultation(@PathVariable Long id) {
		Consultation oneConsultation = consultationService.findOneById(id);
		return new ResponseEntity<>(oneConsultation, HttpStatus.OK);
	}
	
	// Update One Consultation
	@PutMapping("/consultations/{id}")
	public ResponseEntity<Consultation> updateOneConsultationStatus(@PathVariable Long id, @RequestBody Consultation consultation) {
	    Consultation existingConsultation = consultationService.findOneById(id);
	    if (existingConsultation != null) {
	        existingConsultation.setStatus(consultation.getStatus());
	        Consultation updatedConsultation = consultationService.update(existingConsultation);
	        return new ResponseEntity<>(updatedConsultation, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}


	// Delete a consultation by ID
	@DeleteMapping("/consultations/{id}")
	public ResponseEntity<Consultation> deleteConsultation(@PathVariable Long id) {
		consultationService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
