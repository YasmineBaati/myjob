package com.anis.project.services;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.anis.project.models.PatientInformation;
import com.anis.project.repositories.PatientInformationRepository;

@Service
public class PatientInformationService {
	private final PatientInformationRepository patientInformationRepository;

	public PatientInformationService(PatientInformationRepository patientInformationRepository) {
		this.patientInformationRepository = patientInformationRepository;
	}
	
	// Display all PatientInformations
	public List<PatientInformation> allPatientInformations() {
		return patientInformationRepository.findAll();
	}

	// Create a patientInformation
	public PatientInformation createPatientInformation(PatientInformation patientInformation) {
		return patientInformationRepository.save(patientInformation);
		
	}

	// Find one
	public PatientInformation findPatientInformation(Long id) {
		Optional<PatientInformation> optionalPatientInformation = patientInformationRepository.findById(id);
		if (optionalPatientInformation.isPresent()) {
			return optionalPatientInformation.get();
		} else {
			return null;
		}
	}

	
	// Delete a patientInformation
	public void deletePatientInformation(Long id) {
		patientInformationRepository.deleteById(id);
	}

	// Update a patientInformation
	public PatientInformation updatePatientInformation(PatientInformation patientInformation) {
		return patientInformationRepository.save(patientInformation);
	}
}
