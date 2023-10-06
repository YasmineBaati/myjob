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

import com.anis.project.models.Doctor;
import com.anis.project.models.LoginDoctor;
import com.anis.project.services.DoctorService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api") // Base path for the API
public class AuthDoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/doctors/register")
    public ResponseEntity<?> register(@Valid @RequestBody Doctor newDoctor, BindingResult result,
                                      HttpSession session) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        Doctor registredDoctor = doctorService.register(newDoctor, result);
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        session.setAttribute("doctor_id", newDoctor.getId());
        return new ResponseEntity<>(registredDoctor,HttpStatus.CREATED);
    }

    @PostMapping("/doctors/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDoctor newLogin, BindingResult result,
                                   HttpSession session) {
        Doctor doctor = doctorService.login(newLogin, result);
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        session.setAttribute("doctor_id", doctor.getId());
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @GetMapping("/doctors/logout")
    public ResponseEntity<?> logout(HttpSession s) {
        s.invalidate();
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
