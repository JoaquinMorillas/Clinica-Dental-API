package com.joaquin.ClinicaMVC.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Dentist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer registration;
    private String name;
    private String lastName;

    @OneToMany(mappedBy = "dentitst", fetch = FetchType.EAGER)
    private Set<Appointment> appointments = new HashSet<>();
    
    public Set<Appointment> getAppointments() {
        return appointments;
    }
    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getRegistration() {
        return registration;
    }
    public void setRegistration(Integer registration) {
        this.registration = registration;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
}
