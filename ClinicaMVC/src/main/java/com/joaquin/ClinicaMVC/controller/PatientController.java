package com.joaquin.ClinicaMVC.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;

import com.joaquin.ClinicaMVC.entity.Patient;
import com.joaquin.ClinicaMVC.exception.ResourceNotFoundException;
import com.joaquin.ClinicaMVC.service.PatientService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/patient")
public class PatientController {


    @Autowired
    private PatientService patientService;



    @PostMapping("/save")
    public Patient save(@RequestBody Patient patient) {
        return patientService.save(patient);
    }
    
    @GetMapping()
    public List<Patient> findAll(){
        return patientService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(@PathVariable Integer id){
        Optional<Patient> patient = patientService.findById(id);

        if(patient.isPresent()){
            return ResponseEntity.ok(patient.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Patient> deleteById (@PathVariable Integer id) throws ResourceNotFoundException {
        Optional<Patient> patientToDelete = patientService.deleteById(id);
        return ResponseEntity.ok(patientToDelete.get());
    }
}
