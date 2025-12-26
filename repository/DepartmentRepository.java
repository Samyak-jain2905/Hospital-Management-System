package com.samyaksProject.HospitalManagement.repository;

import com.samyaksProject.HospitalManagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}