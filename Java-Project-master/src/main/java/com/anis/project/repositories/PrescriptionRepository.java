package com.anis.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anis.project.models.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
	List <Prescription> findAll();
    List<Prescription> findAllByPatientId(Long patientId);

}
