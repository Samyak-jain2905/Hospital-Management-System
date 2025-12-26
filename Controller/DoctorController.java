package com.samyaksProject.HospitalManagement.Controller;



import com.samyaksProject.HospitalManagement.DTO.AppointmentResponseDto;
import com.samyaksProject.HospitalManagement.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor


    public class DoctorController {

        private final AppointmentService appointmentService;

        @GetMapping("/{doctorId}/appointments")
        public ResponseEntity<List<AppointmentResponseDto>> getAllAppointmentsOfDoctor(@PathVariable Long doctorId) {
            List<AppointmentResponseDto> appointments = appointmentService.getAppointmentsByDoctorId(doctorId);
            return ResponseEntity.ok(appointments);
        }
    }

