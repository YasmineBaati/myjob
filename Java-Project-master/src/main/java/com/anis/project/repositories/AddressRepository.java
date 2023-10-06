package com.anis.project.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.anis.project.models.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {
	List <Address> findAll();

}
