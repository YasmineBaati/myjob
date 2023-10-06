package com.anis.project.services;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.anis.project.models.Medicine;
import com.anis.project.repositories.MedicineRepository;

@Service
public class MedicineService {
	private final MedicineRepository medicineRepository;

	public MedicineService(MedicineRepository medicineRepository) {
		this.medicineRepository = medicineRepository;
	}
	
	// Display all Medicines
	public List<Medicine> allMedicines() {
		return medicineRepository.findAll();
	}

	// Create a medicine
	public Medicine createMedicine(Medicine medicine) {
		return medicineRepository.save(medicine);
		
	}

	// Find one
	public Medicine findMedicine(Long id) {
		Optional<Medicine> optionalMedicine = medicineRepository.findById(id);
		if (optionalMedicine.isPresent()) {
			return optionalMedicine.get();
		} else {
			return null;
		}
	}

	
	// Delete a medicine
	public void deleteMedicine(Long id) {
		medicineRepository.deleteById(id);
	}

	// Update a medicine
	public Medicine updateMedicine(Medicine medicine) {
		return medicineRepository.save(medicine);
	}
}
