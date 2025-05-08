package com.joaquin.ClinicaMVC.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joaquin.ClinicaMVC.entity.Dentist;
import com.joaquin.ClinicaMVC.exception.ResourceNotFoundException;
import com.joaquin.ClinicaMVC.repository.DentistRepository;

@Service
public class DentistService {


    @Autowired
    private DentistRepository dentistRepository;



    public Dentist save(Dentist dentitst){
        return dentistRepository.save(dentitst);
    }

    public Dentist update(Dentist dentitst){
        return dentistRepository.save(dentitst);
    }

    public Optional<Dentist> findById(Integer id) {
        return dentistRepository.findById(id);
    }

    public List<Dentist> findAll() {
        return dentistRepository.findAll();
    }

    public Optional<Dentist> deleteDentist(Integer id) throws ResourceNotFoundException{
        Optional<Dentist> dentistToDelete = this.findById(id);

        if (dentistToDelete.isPresent()){
            dentistRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No se encontro al odontologo con id: " + id);
        }

        return dentistToDelete;
     }

}
