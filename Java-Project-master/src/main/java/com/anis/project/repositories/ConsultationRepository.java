package com.anis.project.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.anis.project.models.Consultation;

public interface ConsultationRepository extends CrudRepository<Consultation, Long> {
//   //FIND All


   List<Consultation> findAll();
//    //FIND BY ID

Optional<Consultation> findById(Long aLong);
//

}