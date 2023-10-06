package com.anis.project.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.anis.project.models.DoctorInformation;

public interface DoctorInformationRepository extends CrudRepository<DoctorInformation, Long> {
	List <DoctorInformation> findAll();
}
