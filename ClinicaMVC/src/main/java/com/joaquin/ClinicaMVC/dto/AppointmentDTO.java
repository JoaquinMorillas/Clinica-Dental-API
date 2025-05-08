package com.joaquin.ClinicaMVC.dto;


public class AppointmentDTO {

    private Integer id;
    private Integer dentist_id;
    private Integer patient_id;
    private String date;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getDentist_id() {
        return dentist_id;
    }
    public void setDentist_id(Integer dentist_id) {
        this.dentist_id = dentist_id;
    }
    public Integer getPatient_id() {
        return patient_id;
    }
    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    
    
}
