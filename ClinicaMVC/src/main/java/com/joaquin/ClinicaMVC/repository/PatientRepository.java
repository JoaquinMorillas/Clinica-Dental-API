package com.joaquin.ClinicaMVC.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaquin.ClinicaMVC.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Optional<Patient> findByLastName(String lastName);
}
