package com.anis.project.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.anis.project.models.PatientInformation;

public interface PatientInformationRepository extends CrudRepository<PatientInformation, Long> {
	List <PatientInformation> findAll();

}
