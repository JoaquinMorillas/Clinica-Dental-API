package com.joaquin.ClinicaMVC.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaquin.ClinicaMVC.entity.Dentist;
import com.joaquin.ClinicaMVC.exception.ResourceNotFoundException;
import com.joaquin.ClinicaMVC.service.DentistService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/dentist")
public class DentistController {

    @Autowired
    private DentistService dentistService;

    @GetMapping("/{id}")
    public Optional<Dentist> findById(@PathVariable Integer id){
        return dentistService.findById(id);
    }

    @PostMapping
    public Dentist saveDentist(@RequestBody Dentist dentitst){
        return dentistService.save(dentitst);
    }
    
    @PutMapping
    public Dentist updateDentist(@RequestBody Dentist dentitst){
        return dentistService.update(dentitst);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Dentist> deleteDentist(@PathVariable Integer id) throws ResourceNotFoundException{
        Optional<Dentist> dentistToDelete = dentistService.deleteDentist(id);
        return ResponseEntity.ok(dentistToDelete.get());
    }

    @GetMapping()
    public List<Dentist> findAll(){
        return dentistService.findAll();
    }

    
}
