package com.anis.project.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.anis.project.models.Doctor;
import com.anis.project.models.Medicine;
import com.anis.project.models.Patient;
import com.anis.project.models.Prescription;
import com.anis.project.services.DoctorService;
import com.anis.project.services.MedicineService;
import com.anis.project.services.PatientService;
import com.anis.project.services.PrescriptionService;

import jakarta.transaction.Transactional;
@RestController
@RequestMapping("/api") 
public class PrescriptionController {

	@Autowired
	private PrescriptionService prescriptionService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private MedicineService medicineService;

	

	// Create One Prescription
	@PostMapping(value = "/prescriptions/{doctorId}/{patientId}")
	public ResponseEntity<Prescription> createPrescription(
	    @RequestBody Map<String, Object> prescriptionData,
	    @PathVariable("doctorId") Long doctorId,
	    @PathVariable("patientId") Long patientId) {
	    // Extract prescription and medicines data from the request body
	    @SuppressWarnings("unchecked")
	    List<Map<String, Object>> medicinesData = (List<Map<String, Object>>) prescriptionData.get("medicines");

	    // find doctor and patient instances
	    Doctor doctor = doctorService.findDoctor(doctorId);
	    Patient patient = patientService.findPatient(patientId);

	    // Create a new Prescription instance
	    Prescription prescription = new Prescription();
	    prescription.setDoctor(doctor);
	    prescription.setPatient(patient);

	    // Create and associate medicines with the prescription
	    List<Medicine> medicines = new ArrayList<>();
	    for (Map<String, Object> medicineData : medicinesData) {
	        Medicine medicine = new Medicine();
	        medicine.setMedicineName((String) medicineData.get("medicineName"));
	        medicine.setMorning((Integer) medicineData.get("morning"));
	        medicine.setAfternoon((Integer) medicineData.get("afternoon"));
	        medicine.setNight((Integer) medicineData.get("night"));
	        medicine.setPrescription(prescription);
	        medicines.add(medicine);
	    }

	    // Set the medicines for the prescription
	    prescription.setMedicines(medicines);

	    // Create the prescription in the database first
	    Prescription prescriptionCreated = prescriptionService.createPrescription(prescription);

	    // Now that the prescription is saved, you can associate medicines with it and save them
	    for (Medicine medicine : medicines) {
	        medicine.setPrescription(prescriptionCreated);
	        medicineService.createMedicine(medicine);
	    }

	    return new ResponseEntity<>(prescriptionCreated, HttpStatus.CREATED);
	}

	

	// Get All Prescriptions
	@GetMapping("/prescriptions")
	public ResponseEntity<List<Prescription>> getAllPrescriptions() {
		List<Prescription> allPrescriptions = prescriptionService.allPrescriptions();
		return new ResponseEntity<>(allPrescriptions, HttpStatus.OK);
	}
	
	@GetMapping("/prescriptions/patient/{patientId}")
    public ResponseEntity<List<Prescription>> getAllPrescriptionsByPatientId(@PathVariable Long patientId) {
        List<Prescription> prescriptions = prescriptionService.getAllPrescriptionsByPatientId((Long)patientId);
        return new ResponseEntity<>(prescriptions, HttpStatus.OK);
    }

	// Find One Prescription
	@GetMapping("/prescriptions/{id}")
	public ResponseEntity<Prescription> getOnePrescription(@PathVariable Long id) {
		Prescription onePrescription = prescriptionService.findPrescription(id);
		return new ResponseEntity<>(onePrescription, HttpStatus.OK);
	}
	
	// Update One Prescription
	@PutMapping("/prescriptions/{id}")
	public ResponseEntity<Prescription> updateOnePrescription(@PathVariable Long id, @RequestBody Prescription prescription) {
		Prescription existingPrescription = prescriptionService.findPrescription(id);
		if (existingPrescription != null) {
			
			Prescription updatedPrescription = prescriptionService.updatePrescription(prescription);
			return new ResponseEntity<>(updatedPrescription, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Delete a prescription by ID
	@DeleteMapping("/prescriptions/{id}")
	public ResponseEntity<Prescription> deletePrescription(@PathVariable Long id) {
		prescriptionService.deletePrescription(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
