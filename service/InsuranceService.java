package com.samyaksProject.HospitalManagement.service;

import com.samyaksProject.HospitalManagement.entity.Insurance;
import com.samyaksProject.HospitalManagement.entity.Patient;
import com.samyaksProject.HospitalManagement.repository.InsuranceRepository;
import com.samyaksProject.HospitalManagement.repository.PatientRepository;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final PatientRepository patientRepository;
    private final InsuranceRepository insuranceRepository;

    @Transactional
    public Patient assignInsuanceToPatient(Insurance insurance,Long patientId){
        Patient patient=patientRepository.findById(patientId).orElseThrow(()->new EntityNotFoundException());
        patient.setInsurance(insurance);
        insurance.setPatient(patient); //bidirectional consistancy maintainaces
        return patient;
    }
    @Transactional
    public Patient disaccociateInsuranceFromPatient(Long patientId){
        Patient patient=patientRepository.findById(patientId).orElseThrow(()->new EntityNotFoundException());
   patient.setInsurance(null);
   return patient;
    }
}


