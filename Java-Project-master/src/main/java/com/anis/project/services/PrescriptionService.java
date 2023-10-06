package com.anis.project.services;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.anis.project.models.Prescription;
import com.anis.project.repositories.PrescriptionRepository;

@Service
public class PrescriptionService {
	private final PrescriptionRepository prescriptionRepository;

	public PrescriptionService(PrescriptionRepository prescriptionRepository) {
		this.prescriptionRepository = prescriptionRepository;
	}
	
	// Display all Prescriptions
	public List<Prescription> allPrescriptions() {
		return prescriptionRepository.findAll();
	}

	public List<Prescription> getAllPrescriptionsByPatientId(Long patientId) {
        return prescriptionRepository.findAllByPatientId(patientId);
    }
	
	// Create a prescription
	public Prescription createPrescription(Prescription prescription) {
		return prescriptionRepository.save(prescription);
		
	}

	// Find one
	public Prescription findPrescription(Long id) {
		Optional<Prescription> optionalPrescription = prescriptionRepository.findById(id);
		if (optionalPrescription.isPresent()) {
			return optionalPrescription.get();
		} else {
			return null;
		}
	}

	
	// Delete a prescription
	public void deletePrescription(Long id) {
		prescriptionRepository.deleteById(id);
	}

	// Update a prescription
	public Prescription updatePrescription(Prescription prescription) {
		return prescriptionRepository.save(prescription);
	}
}
