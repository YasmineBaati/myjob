package com.anis.project.services;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.anis.project.models.Address;
import com.anis.project.repositories.AddressRepository;

@Service
public class AddressService {
	private final AddressRepository addressRepository;

	public AddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
	

	// Display all Addresses
	public List<Address> allAddresses() {
		return addressRepository.findAll();
	}

	// Create a address
	public Address createAddress(Address address) {
		

		return addressRepository.save(address);
		
	}

	// Find one
	public Address findAddress(Long id) {
		Optional<Address> optionalAddress = addressRepository.findById(id);
		if (optionalAddress.isPresent()) {
			return optionalAddress.get();
		} else {
			return null;
		}
	}

	
	// Delete a address
	public void deleteAddress(Long id) {
		addressRepository.deleteById(id);
	}

	// Update a address
	public Address updateAddress(Address address) {
		return addressRepository.save(address);
	}
}
