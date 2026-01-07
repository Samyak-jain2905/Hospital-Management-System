package com.samyaksProject.HospitalManagement.Controller;

import com.samyaksProject.HospitalManagement.DTO.DoctorResponseDto;
import com.samyaksProject.HospitalManagement.DTO.OnBoardNewDoctorRequestDTO;
import com.samyaksProject.HospitalManagement.DTO.PatientResponseDto;
import com.samyaksProject.HospitalManagement.service.DoctorService;
import com.samyaksProject.HospitalManagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final PatientService patientService;
    private final DoctorService doctorService;

    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponseDto>> getAllPatients(
            @RequestParam(value = "page", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize
    ) {

        return ResponseEntity.ok(patientService.getAllPatients(pageNumber, pageSize));
    }

 @PostMapping("/onBoardNewDoctor")
    public ResponseEntity<DoctorResponseDto> OnboardNewdoctor(@RequestBody OnBoardNewDoctorRequestDTO onBoardNewDoctorRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.onBoardNewDoctor(onBoardNewDoctorRequestDTO));
 }

}