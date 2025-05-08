package com.joaquin.ClinicaMVC.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.joaquin.ClinicaMVC.dto.AppointmentDTO;
import com.joaquin.ClinicaMVC.entity.Appointment;
import com.joaquin.ClinicaMVC.exception.ResourceNotFoundException;
import com.joaquin.ClinicaMVC.repository.AppointmentRepository;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DentistService dentistService;
    @Autowired
    private PatientService patientService;

    public AppointmentDTO save(AppointmentDTO appointmentDTO){

        Appointment appointment = new Appointment();

        appointment.setId(appointmentDTO.getId());
        appointment.setDentitst(dentistService.findById(appointmentDTO.getDentist_id()).get());

        appointment.setPatient(patientService.findById(appointmentDTO.getPatient_id()).get());

        appointment.setDate(LocalDate.parse(appointmentDTO.getDate()));

        appointmentRepository.save(appointment);

        AppointmentDTO appointmentDTOToReturn = new AppointmentDTO();
        appointmentDTOToReturn.setId(appointment.getId());
        appointmentDTOToReturn.setDate(appointment.getDate().toString());
        appointmentDTOToReturn.setDentist_id(appointment.getDentitst().getId());
        appointmentDTOToReturn.setPatient_id(appointment.getPatient().getId());

        return appointmentDTOToReturn;
    }

    public Optional<AppointmentDTO> findById(Integer id){
        return appointmentRepository.findById(id)
        .map(appointment -> {
            AppointmentDTO dto = new AppointmentDTO();
            dto.setId(appointment.getId());
            dto.setDentist_id(appointment.getDentitst().getId());
            dto.setPatient_id(appointment.getPatient().getId());
            dto.setDate(appointment.getDate().toString());
            return dto;
        });
    }

    public List<AppointmentDTO> findAll(){

        List<AppointmentDTO> appointmentsDTOs = new ArrayList<>();

        List<Appointment> appointments = appointmentRepository.findAll();

        for (Appointment appointment : appointments) {
            AppointmentDTO appointmentDTO = new AppointmentDTO();
            appointmentDTO.setId(appointment.getId());
            appointmentDTO.setDate(appointment.getDate().toString());
            appointmentDTO.setDentist_id(appointment.getDentitst().getId());
            appointmentDTO.setPatient_id(appointment.getPatient().getId());
            appointmentsDTOs.add(appointmentDTO); 
        }

        return appointmentsDTOs;
    }

    public AppointmentDTO updateAppointment(AppointmentDTO appointmentDTO) throws Exception{
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentDTO.getId());
        if(!appointment.isPresent()){
            throw new Exception("Appointment Not Found");
        }else{
            AppointmentDTO appointmentDTOToReturn = new AppointmentDTO();
            Appointment appointmentToUpdate = appointment.get();

            appointmentToUpdate.setDate(LocalDate.parse(appointmentDTO.getDate()));
            appointmentToUpdate.setDentitst(dentistService.findById(appointmentDTO.getDentist_id()).get());
            appointmentToUpdate.setPatient(patientService.findById(appointmentDTO.getPatient_id()).get());

            appointmentRepository.save(appointmentToUpdate);

            appointmentDTOToReturn.setDate(appointmentToUpdate.getDate().toString());
            appointmentDTOToReturn.setDentist_id(appointmentToUpdate.getDentitst().getId());
            appointmentDTOToReturn.setPatient_id(appointmentToUpdate.getPatient().getId());
            appointmentDTOToReturn.setId(appointmentToUpdate.getId());

            return appointmentDTOToReturn;
        }

    }

    public Optional<AppointmentDTO> deleteById(Integer id) throws ResourceNotFoundException{
        Optional<AppointmentDTO> appointmentToDelete = this.findById(id);

        if (appointmentToDelete.isPresent()){

            appointmentRepository.deleteById(id);

        } else {
            throw new ResourceNotFoundException("No se encontró el turno con id: " + id);
        }
        return appointmentToDelete;
    }


}
