package com.joaquin.ClinicaMVC.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.joaquin.ClinicaMVC.entity.Address;
import com.joaquin.ClinicaMVC.entity.Appointment;
import com.joaquin.ClinicaMVC.entity.Dentist;
import com.joaquin.ClinicaMVC.entity.Patient;
import com.joaquin.ClinicaMVC.repository.AppointmentRepository;
import com.joaquin.ClinicaMVC.repository.DentistRepository;
import com.joaquin.ClinicaMVC.repository.PatientRepository;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(OrderAnnotation.class)
public class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private  PatientRepository patientRepository;
    @Autowired
    private  DentistRepository dentistRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @BeforeEach
    public  void loadPatient(){
        Patient patient = new Patient();
        patient.setAddress(new Address());
        patient.setCardId(123456);
        patient.setDateOfAddimision(LocalDate.of(2025,4,29));
        patient.setLastName("morillas");
        patient.setName("joaquin");
        patient.setAppointments(new HashSet<Appointment>());
        patientRepository.save(patient);
    }
    @BeforeEach
    public void loadDentist(){
        Dentist dentist = new Dentist();
        dentist.setName("cacho");
        dentist.setLastName("castaña");
        dentist.setRegistration(1234);
        dentistRepository.save(dentist);
    }

    @Test
    @Order(2)
    void testFindAll() throws Exception {
        mockMvc.perform(get("/appointment")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").isArray())
                        .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    @Order(3)
    void testFindById() throws Exception {
        mockMvc.perform(get("/appointment/1")
                            .accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("id").value("1"));
    }

    @Test
    @Order(1)
    void testSave() throws Exception {
        loadDentist();
        loadPatient();
        

        String appointmentData = "{\"dentist_id\":\"1\"," +
                                  "\"patient_id\":\"1\"," +
                                  "\"date\":\"2025-05-01\"}";
                                  
        mockMvc.perform(post("/appointment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(appointmentData)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("dentist_id").value("1"))
                        .andExpect(jsonPath("patient_id").value("1"));

    }
}
