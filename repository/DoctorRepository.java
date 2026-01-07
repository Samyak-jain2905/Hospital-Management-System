package com.samyaksProject.HospitalManagement.repository;

import com.samyaksProject.HospitalManagement.entity.Doctor;
import com.samyaksProject.HospitalManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    boolean existsByUser(User user);
}