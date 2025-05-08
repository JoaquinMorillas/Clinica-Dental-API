package com.joaquin.ClinicaMVC.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaquin.ClinicaMVC.dto.AppointmentDTO;

import com.joaquin.ClinicaMVC.entity.Dentist;
import com.joaquin.ClinicaMVC.entity.Patient;
import com.joaquin.ClinicaMVC.exception.ResourceNotFoundException;
import com.joaquin.ClinicaMVC.service.AppointmentService;
import com.joaquin.ClinicaMVC.service.DentistService;
import com.joaquin.ClinicaMVC.service.PatientService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private DentistService dentistService;
    @Autowired
    private PatientService patientService;

    

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> findById(@PathVariable Integer id){
        Optional<AppointmentDTO> appointment = appointmentService.findById(id);

        if(appointment.isPresent()){
            return ResponseEntity.ok(appointment.get());
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<AppointmentDTO> save(@RequestBody AppointmentDTO appointmentDTO){
        
        ResponseEntity<AppointmentDTO> response;
        
        Optional<Dentist> dentist = dentistService.findById(appointmentDTO.getDentist_id());
       
        Optional<Patient> patient = patientService.findById(appointmentDTO.getPatient_id());
        
        if(dentist.isPresent() && patient.isPresent()){
            response = ResponseEntity.ok(appointmentService.save(appointmentDTO));
        }
        else{
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    
        return response;
        //return ResponseEntity.ok(appointmentService.save(appointment));
    }
    
    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> findAll(){
        return ResponseEntity.ok(appointmentService.findAll());

    }
    
    @PutMapping
    public ResponseEntity<AppointmentDTO> updateAppointment(@RequestBody AppointmentDTO appointmentDTO) throws Exception{
        ResponseEntity<AppointmentDTO> response;

        if(dentistService.findById(appointmentDTO.getDentist_id()).isPresent() && 
        patientService.findById(appointmentDTO.getPatient_id()).isPresent()){
            response = ResponseEntity.ok(appointmentService.updateAppointment(appointmentDTO));
        }else{
            response = ResponseEntity.badRequest().build();
        }

        return response;
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<AppointmentDTO> deleteAppointment(@PathVariable Integer id) throws ResourceNotFoundException{
        Optional<AppointmentDTO> apointmentToDelete = appointmentService.deleteById(id);
        return ResponseEntity.ok(apointmentToDelete.get());
    }
    

}
