package com.anis.project.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.anis.project.models.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
	List <Doctor> findAll();
	Optional <Doctor> findByEmail(String email);


}
