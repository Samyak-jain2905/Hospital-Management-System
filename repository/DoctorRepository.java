package com.samyaksProject.HospitalManagement.repository;

import com.samyaksProject.HospitalManagement.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}