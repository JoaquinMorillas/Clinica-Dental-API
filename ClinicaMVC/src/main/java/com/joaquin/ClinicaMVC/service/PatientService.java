package com.joaquin.ClinicaMVC.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaquin.ClinicaMVC.entity.Patient;
import com.joaquin.ClinicaMVC.exception.ResourceNotFoundException;
import com.joaquin.ClinicaMVC.repository.PatientRepository;

@Service
public class PatientService {

   
    
    @Autowired
    private PatientRepository patientRepository;

    

    public Patient save(Patient patient){
        return patientRepository.save(patient);
    }

    public List<Patient> findAll(){
        return patientRepository.findAll();

    }
    
    public void update(Patient patient){
        patientRepository.save(patient);
    }

    public Optional<Patient> deleteById (Integer id) throws ResourceNotFoundException{
        Optional<Patient> patientToDelete = this.findById(id);
        if (patientToDelete.isPresent()){
            patientRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No se encontró al paciente con id: " + id);
        }

        return patientToDelete;
    }

    public Optional<Patient> findById(Integer id){
        return patientRepository.findById(id);
    }

    public Optional<Patient> findByLastName(String lastName){
        return patientRepository.findByLastName(lastName);
    }
}
