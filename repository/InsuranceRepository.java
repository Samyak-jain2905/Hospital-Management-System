package com.samyaksProject.HospitalManagement.repository;

import com.samyaksProject.HospitalManagement.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}