package com.joaquin.ClinicaMVC.controller;

import java.time.LocalDate;

import java.util.HashSet;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import com.joaquin.ClinicaMVC.entity.Address;
import com.joaquin.ClinicaMVC.entity.Appointment;
import com.joaquin.ClinicaMVC.entity.Patient;
import com.joaquin.ClinicaMVC.repository.PatientRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)

public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PatientRepository patientRepository;

    public void loadData(){
        Patient patient = new Patient();
        patient.setAddress(new Address());
        patient.setCardId(123456);
        patient.setDateOfAddimision(LocalDate.of(2025,4,29));
        patient.setLastName("morillas");
        patient.setName("joaquin");
        patient.setAppointments(new HashSet<Appointment>());
        patientRepository.save(patient);

    }

    @Test
    public void testGetPatientById() throws Exception{
        loadData();
        mockMvc.perform(get("/patient/1")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name").value("joaquin"))
                        .andExpect(jsonPath("$.lastName").value("morillas"))
                        ;
    }

    @Test
    public void testSavePatient() throws Exception{
        String patientSaved = "{\r\n" + //
                        "    \"name\": \"joaquin\",\r\n" + //
                        "    \"lastName\": \"morillas\",\r\n" + //
                        "    \"cardId\": \"123465\",\r\n" + //
                        "    \"dateOfAddimision\": \"2025-03-05\",\r\n" + //
                        "    \"address\":{\r\n" + //
                        "        \"street\": \"urquiza\",\r\n" + //
                        "        \"number\": \"123\",\r\n" + //
                        "        \"location\": \"Rio Cuarto\",\r\n" + //
                        "        \"province\": \"Cordoba\"\r\n" + //
                        "    }\r\n" + //
                        "}";

        mockMvc.perform(post("/patient/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientSaved)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name").value("joaquin"))
                        .andExpect(jsonPath("$.lastName").value("morillas"));           
    }
}
