package com.anis.project.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.anis.project.models.Patient;

public interface PatientRepository extends CrudRepository<Patient,Long> {
    //FIND All
	Optional <Patient> findByEmail(String email);

    List<Patient> findAll();
}