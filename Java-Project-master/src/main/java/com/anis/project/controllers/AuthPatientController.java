package com.anis.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anis.project.models.LoginPatient;
import com.anis.project.models.Patient;
import com.anis.project.services.PatientService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api") // Base path for the API
public class AuthPatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/patients/register")
    public ResponseEntity<?> register(@Valid @RequestBody Patient newPatient, BindingResult result,
                                      HttpSession session) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        Patient thisPatient = patientService.register(newPatient, result);
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        session.setAttribute("patient_id", newPatient.getId());
        return new ResponseEntity<>(thisPatient,HttpStatus.CREATED);
    }

    @PostMapping("/patients/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginPatient newLogin, BindingResult result,
                                   HttpSession session) {
        Patient patient = patientService.login(newLogin, result);
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        session.setAttribute("patient_id", patient.getId());
        return new ResponseEntity<>(patient,HttpStatus.OK);
    }

    @GetMapping("/patients/logout")
    public ResponseEntity<?> logout(HttpSession s) {
        s.invalidate();
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
