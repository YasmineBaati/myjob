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

import com.anis.project.models.Address;
import com.anis.project.models.Doctor;
import com.anis.project.services.AddressService;
import com.anis.project.services.DoctorService;

@RestController
@RequestMapping("/api") 
public class AddressController {

	@Autowired
	private AddressService addressService;
	@Autowired
	private DoctorService doctorService;
	// Create One Address
	@PostMapping(value = "/addresses/{doctorId}")
	public ResponseEntity<Address> createAddress(
	        @RequestBody Address address,
	        @PathVariable("doctorId") Long doctorId) {
	    Doctor doctor = doctorService.findDoctor((Long)doctorId);
	    address.setDoctor(doctor);
	    Address addressCreated = addressService.createAddress(address);
	    return new ResponseEntity<>(addressCreated, HttpStatus.CREATED);
	}


	// Get All Addresses
	@GetMapping("/addresses")
	public ResponseEntity<List<Address>> getAllAddresss() {
		List<Address> allAddresses = addressService.allAddresses();
		return new ResponseEntity<>(allAddresses, HttpStatus.OK);
	}

	// Find One Address
	@GetMapping("/addresses/{id}")
	public ResponseEntity<Address> getOneAddress(@PathVariable Long id) {
		Address oneAddress = addressService.findAddress(id);
		return new ResponseEntity<>(oneAddress, HttpStatus.OK);
	}
	
	// Update One Address
	@PutMapping("/addresses/{id}")
	public ResponseEntity<Address> updateOneAddress(@PathVariable Long id, @RequestBody Address address) {
		Address existingAddress = addressService.findAddress(id);
		if (existingAddress != null) {
			existingAddress.setCity(address.getCity());
			existingAddress.setState(address.getState());
			existingAddress.setStreet(address.getStreet());

			Address updatedAddress = addressService.updateAddress(address);
			return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Delete a consultation by ID
	@DeleteMapping("/addresses/{id}")
	public ResponseEntity<Address> deleteAddress(@PathVariable Long id) {
		addressService.deleteAddress(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
