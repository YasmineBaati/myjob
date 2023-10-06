package com.anis.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anis.project.models.Medicine;
import com.anis.project.services.MedicineService;

@RestController
@RequestMapping("/api") 
public class MedicineController {

	@Autowired
	private MedicineService medicineService;

	// Create One Medicine
	@PostMapping(value = "/medicines")
	public ResponseEntity<Medicine> createMedicine(@RequestBody Medicine medicine) {
		Medicine medicineCreated = medicineService.createMedicine(medicine);
		return new ResponseEntity<>(medicineCreated, HttpStatus.CREATED);
	}

	// Get All Medicines
	@GetMapping("/medicines")
	public ResponseEntity<List<Medicine>> getAllMedicines() {
		List<Medicine> allMedicines = medicineService.allMedicines();
		return new ResponseEntity<>(allMedicines, HttpStatus.OK);
	}

	// Find One Medicine
	@GetMapping("/medicines/{id}")
	public ResponseEntity<Medicine> getOneMedicine(@PathVariable Long id) {
		Medicine oneMedicine = medicineService.findMedicine(id);
		return new ResponseEntity<>(oneMedicine, HttpStatus.OK);
	}
	
	// Update One Medicine
	@PutMapping("/medicines/{id}")
	public ResponseEntity<Medicine> updateOneMedicine(@PathVariable Long id, @RequestBody Medicine medicine) {
		Medicine existingMedicine = medicineService.findMedicine(id);
		if (existingMedicine != null) {
			existingMedicine.setMedicineName(medicine.getMedicineName());
			existingMedicine.setMorning(medicine.getMorning());
			existingMedicine.setAfternoon(medicine.getAfternoon());
			existingMedicine.setNight(medicine.getNight());
			Medicine updatedMedicine = medicineService.updateMedicine(medicine);
			return new ResponseEntity<>(updatedMedicine, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Delete a consultation by ID
	@DeleteMapping("/medicines/{id}")
	public ResponseEntity<Medicine> deleteMedicine(@PathVariable Long id) {
		medicineService.deleteMedicine(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
