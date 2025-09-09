package com.samyaksProject.HospitalManagement;

import com.samyaksProject.HospitalManagement.entity.Appointment;
import com.samyaksProject.HospitalManagement.entity.Insurance;
import com.samyaksProject.HospitalManagement.entity.Patient;
import com.samyaksProject.HospitalManagement.entity.type.BloodGroupType;
import com.samyaksProject.HospitalManagement.service.InsuranceService;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class InsuranceTest {
    @Autowired
    private InsuranceService insuranceService;
    @Test
    public void testInsurance() {
        Insurance insurance = Insurance.builder()
                .policyNumber("SBI_29")
                .provider("SBI")
                .validUntil(LocalDate.of(2040, 12, 12))
                .build();
        Patient patient=insuranceService.assignInsuanceToPatient(insurance,1L);

        var newPatient=insuranceService.disaccociateInsuranceFromPatient(patient.getId());
        System.out.println(newPatient);


    }
}



