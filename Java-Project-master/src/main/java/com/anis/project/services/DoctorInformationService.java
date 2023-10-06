package com.anis.project.services;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.anis.project.models.DoctorInformation;
import com.anis.project.repositories.DoctorInformationRepository;

@Service
public class DoctorInformationService {
	private final DoctorInformationRepository doctorInformationRepository;

	public DoctorInformationService(DoctorInformationRepository doctorInformationRepository) {
		this.doctorInformationRepository = doctorInformationRepository;
	}
	
	// Display all DoctorInformations
	public List<DoctorInformation> allDoctorInformations() {
		return doctorInformationRepository.findAll();
	}

	// Create a doctorInformation
	public DoctorInformation createDoctorInformation(DoctorInformation doctorInformation) {
		return doctorInformationRepository.save(doctorInformation);
		
	}

	// Find one
	public DoctorInformation findDoctorInformation(Long id) {
		Optional<DoctorInformation> optionalDoctorInformation = doctorInformationRepository.findById(id);
		if (optionalDoctorInformation.isPresent()) {
			return optionalDoctorInformation.get();
		} else {
			return null;
		}
	}

	
	// Delete a doctorInformation
	public void deleteDoctorInformation(Long id) {
		doctorInformationRepository.deleteById(id);
	}

	// Update a doctorInformation
	public DoctorInformation updateDoctorInformation(DoctorInformation doctorInformation) {
		return doctorInformationRepository.save(doctorInformation);
	}
}
