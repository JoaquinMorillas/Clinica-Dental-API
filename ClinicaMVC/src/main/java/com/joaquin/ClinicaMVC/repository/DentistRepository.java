package com.joaquin.ClinicaMVC.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaquin.ClinicaMVC.entity.Dentist;

public interface DentistRepository extends JpaRepository<Dentist, Integer>{

}
