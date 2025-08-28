package com.shvmdev.patientservice.mapper;

import com.shvmdev.patientservice.dto.PatientRequestDTO;
import com.shvmdev.patientservice.dto.PatientResponseDTO;
import com.shvmdev.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {

    public static PatientResponseDTO toPatientResponseDTO(Patient p){
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        patientResponseDTO.setId(p.getId().toString());
        patientResponseDTO.setName(p.getName());
        patientResponseDTO.setEmail(p.getEmail());
        patientResponseDTO.setAddress(p.getAddress());
        patientResponseDTO.setDateOfBirth(p.getDateOfBirth().toString());
        return patientResponseDTO;
    }

    public static Patient toPatient(PatientRequestDTO patientRequestDTO){
        Patient patient = new Patient();
        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
        return patient;
    }
}
