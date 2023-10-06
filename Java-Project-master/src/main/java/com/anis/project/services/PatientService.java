package com.anis.project.services;


import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.anis.project.models.LoginPatient;
import com.anis.project.models.Patient;
import com.anis.project.repositories.PatientRepository;

@Service
public class PatientService {
	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}
	private final PatientRepository patientRepository;

	
	// Register Patient
		public Patient register(Patient newPatient, BindingResult result) {

			Optional<Patient> potentialPatient = patientRepository.findByEmail(newPatient.getEmail());
			if (potentialPatient.isPresent()) {
				result.rejectValue("email", "registerError", "Email is Taken");
			}
			if (!newPatient.getPassword().equals(newPatient.getConfirmPassword())) {
				result.rejectValue("password", "registerError", "password does not match");
			}
			if (result.hasErrors()) {
				return null;
			} else {
				String hashdPW = BCrypt.hashpw(newPatient.getPassword(), BCrypt.gensalt());
				newPatient.setPassword(hashdPW);
				return patientRepository.save(newPatient);

			}

		}
		
		// Login Patient
		
		public Patient login(LoginPatient newLoginObject, BindingResult result) {
			Optional<Patient> potentialLogin = patientRepository.findByEmail(newLoginObject.getEmail());
			if (!potentialLogin.isPresent()) {
				result.rejectValue("email", "loginError", "email not found");
			} else {
				Patient actualPatient = potentialLogin.get();
				if (!BCrypt.checkpw(newLoginObject.getPassword(), actualPatient.getPassword())) {
					result.rejectValue("password", "loginError", "password incorrect");
				} if (result.hasErrors()) {
					return null;
				} else {
					return actualPatient;
				}
			}
			return null;
		}
	
	

	
	// Display all Patients
	public List<Patient> allPatients() {
		return patientRepository.findAll();
	}

	// Create a patient
	public Patient createPatient(Patient patient) {
		return patientRepository.save(patient);
		
	}

	// Find one
	public Patient findPatient(Long id) {
		Optional<Patient> optionalPatient = patientRepository.findById(id);
		if (optionalPatient.isPresent()) {
			return optionalPatient.get();
		} else {
			return null;
		}
	}

	
	// Delete a patient
	public void deletePatient(Long id) {
		patientRepository.deleteById(id);
	}

	// Update a patient
	public Patient updatePatient(Patient patient) {
		return patientRepository.save(patient);
	}
}
