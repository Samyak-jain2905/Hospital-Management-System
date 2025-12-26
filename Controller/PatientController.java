package com.samyaksProject.HospitalManagement.Controller;

import com.samyaksProject.HospitalManagement.DTO.AppointmentResponseDto;
import com.samyaksProject.HospitalManagement.DTO.PatientResponseDto;
import com.samyaksProject.HospitalManagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/{patientId}/appointments")
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointmentsOfPatient(@PathVariable Long patientId) {
        List<AppointmentResponseDto> appointments = patientService.getAppointmentsByPatientId(patientId);
        return ResponseEntity.ok(appointments);
    }
//    @GetMapping
//    public ResponseEntity<List<PatientResponseDto>> getAllPatients() {
//        return ResponseEntity.ok(patientService.getAllPatients());
//    }
@GetMapping
public ResponseEntity<List<PatientResponseDto>> getAllPatients(
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size
) {
    return ResponseEntity.ok(patientService.getAllPatients(page, size));
}

}