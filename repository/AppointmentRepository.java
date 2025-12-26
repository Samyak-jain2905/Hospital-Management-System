package com.samyaksProject.HospitalManagement.repository;

import com.samyaksProject.HospitalManagement.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}