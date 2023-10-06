package com.anis.project.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anis.project.models.Consultation;
import com.anis.project.repositories.ConsultationRepository;
import com.anis.project.repositories.DoctorRepository;
import com.anis.project.repositories.PatientRepository;

@Service
public class ConsultationService {
    @Autowired
    ConsultationRepository consultationRepository;
    
    @Autowired
    DoctorRepository doctorRepository;
    
    @Autowired
    PatientRepository patientRepository;
    //create a consultation
    public Consultation create (Consultation consultation){
        return  consultationRepository.save(consultation);
    }

    //Read All Consultation
    public List<Consultation> findAllConsultation(){
        return consultationRepository.findAll();
    }
    //Update
    public Consultation update(Consultation consultation) {
        return consultationRepository.save(consultation);
    }
    //Delete Consultation
    public void delete(Long id) {
        Optional<Consultation> optionalShow = consultationRepository.findById(id);
        if(optionalShow.isPresent()) {
            // CascadeType.REMOVE in Show.java will also delete the reviews associated with that show.
            consultationRepository.deleteById(id);
        } else {
            System.out.println("Consultation does not exist");
        }
    }
    //Find By ID
    public Consultation findOneById(Long id) {
        Optional<Consultation> optionalConsultation = consultationRepository.findById(id);

        if(optionalConsultation.isPresent()) {
            return optionalConsultation.get();
        } else {
            return null;
        }
    }
}