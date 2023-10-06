package com.anis.project.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.anis.project.models.Medicine;

public interface MedicineRepository extends CrudRepository<Medicine, Long> {
	List <Medicine> findAll();
}
