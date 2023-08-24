package com.rang.scheduler.controllers;

import com.rang.scheduler.entities.Patient;

import com.rang.scheduler.repositories.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/patient")
@CrossOrigin("*")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;


    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }

    @PostMapping("/add")
    public ResponseEntity<Patient> addData(@RequestBody Patient data) {

        Patient patient = patientRepository.save(data);

        return ResponseEntity.ok(patient);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Patient> getDataById(@PathVariable Long id) {
        Patient patient = patientRepository.getReferenceById(id);

        if (patient != null) {

            return ResponseEntity.ok(patient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Patient>> getAllData() {
        return ResponseEntity.ok(patientRepository.findAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateData(@PathVariable Long id, @RequestBody Patient newData) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);

        if (optionalPatient.isPresent()) {
            Patient updatePatient = optionalPatient.get();
            patientRepository.save(updatePatient);
            return ResponseEntity.ok("Patient updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteData(@PathVariable Long id) {
        Patient patient = patientRepository.getReferenceById(id);

        if (patient != null) {
            patientRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
