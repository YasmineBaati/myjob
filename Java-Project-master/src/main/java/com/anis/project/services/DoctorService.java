package com.anis.project.services;


import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.anis.project.models.Doctor;
import com.anis.project.models.LoginDoctor;
import com.anis.project.repositories.DoctorRepository;

@Service
public class DoctorService {
	private final DoctorRepository doctorRepository;

	public DoctorService(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}
	// Register Doctor
	public Doctor register(Doctor newDoctor, BindingResult result) {

		Optional<Doctor> potentialDoctor = doctorRepository.findByEmail(newDoctor.getEmail());
		if (potentialDoctor.isPresent()) {
			result.rejectValue("email", "registerError", "Email is Taken");
		}
		if (!newDoctor.getPassword().equals(newDoctor.getConfirmPassword())) {
			result.rejectValue("password", "registerError", "password does not match");
		}
		if (result.hasErrors()) {
			return null;
		} else {
			String hashdPW = BCrypt.hashpw(newDoctor.getPassword(), BCrypt.gensalt());
			newDoctor.setPassword(hashdPW);
			return doctorRepository.save(newDoctor);

		}

	}
	
	// Login Doctor
	
	public Doctor login(LoginDoctor newLoginObject, BindingResult result) {
		Optional<Doctor> potentialLogin = doctorRepository.findByEmail(newLoginObject.getEmail());
		if (!potentialLogin.isPresent()) {
			
			result.rejectValue("email", "loginError", "email not found");
		} else {
			Doctor actualDoctor = potentialLogin.get();
			System.out.println(actualDoctor);

			if (!BCrypt.checkpw(newLoginObject.getPassword(), actualDoctor.getPassword())) {
				result.rejectValue("password", "loginError", "password incorrect");
			} if (result.hasErrors()) {
				return null;
			} else {
				return actualDoctor;
}
		}
		return null;
	}
	
	
	// Display all Doctors
	public List<Doctor> allDoctors() {
		return doctorRepository.findAll();
	}

	// Create a doctor
	public Doctor createDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
		
	}

	// Find one
	public Doctor findDoctor(Long id) {
		Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
		if (optionalDoctor.isPresent()) {
			return optionalDoctor.get();
		} else {
			return null;
		}
	}

	
	// Delete a doctor
	public void deleteDoctor(Long id) {
		doctorRepository.deleteById(id);
	}

	// Update a doctor
	public Doctor updateDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

}
