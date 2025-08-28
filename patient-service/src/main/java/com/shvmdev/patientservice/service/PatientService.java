package com.shvmdev.patientservice.service;

import com.shvmdev.patientservice.dto.PatientRequestDTO;
import com.shvmdev.patientservice.dto.PatientResponseDTO;
import com.shvmdev.patientservice.exception.EmailAlreadyExistsException;
import com.shvmdev.patientservice.exception.PatientNotFoundException;
import com.shvmdev.patientservice.grpc.BillingServiceGrpcClient;
import com.shvmdev.patientservice.kafka.KafkaProducer;
import com.shvmdev.patientservice.mapper.PatientMapper;
import com.shvmdev.patientservice.model.Patient;
import com.shvmdev.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final BillingServiceGrpcClient billingServiceGrpcClient;
    private final KafkaProducer kafkaProducer;

    public PatientService(PatientRepository patientRepository, BillingServiceGrpcClient billingServiceGrpcClient, KafkaProducer kafkaProducer) {
        this.patientRepository = patientRepository;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
        this.kafkaProducer = kafkaProducer;
    }

    public List<PatientResponseDTO> getPatients(){
        List<Patient> patientsList = patientRepository.findAll();
        return patientsList.stream().map(PatientMapper::toPatientResponseDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("Patient already exists with given email Id "+patientRequestDTO.getEmail());
        }
        Patient patient = patientRepository.save(PatientMapper.toPatient(patientRequestDTO));
        billingServiceGrpcClient.createBillingAccount(patient.getId().toString(),patient.getName(),patient.getEmail());
        kafkaProducer.sendEvent(patient);
        return PatientMapper.toPatientResponseDTO(patient);
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO){
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new PatientNotFoundException("Patient not found with id "+id));
        if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)){
            throw new EmailAlreadyExistsException("Patient already exists with given email Id "+patientRequestDTO.getEmail());
        }
        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toPatientResponseDTO(updatedPatient);
    }

    public void deletePatient(UUID id){
        patientRepository.deleteById(id);
    }

}
