package com.joaquin.ClinicaMVC.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.joaquin.ClinicaMVC.entity.Patient;


@SpringBootTest
public class PatientServiceTest {
    
    @Autowired
    private PatientService patientService;

    @Test
    void testSave() {
        Patient patient = patientService.save(new Patient());

        assertNotNull(patient);
    }
}
