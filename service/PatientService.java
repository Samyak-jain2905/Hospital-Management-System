package com.samyaksProject.HospitalManagement.service;


import com.samyaksProject.HospitalManagement.DTO.AppointmentResponseDto;
import com.samyaksProject.HospitalManagement.DTO.DoctorResponseDto;
import com.samyaksProject.HospitalManagement.DTO.PatientResponseDto;
import com.samyaksProject.HospitalManagement.entity.Patient;
import com.samyaksProject.HospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public PatientResponseDto getPatientById(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient Not " +
                "Found with id: " + patientId));
        return modelMapper.map(patient, PatientResponseDto.class);
    }

    public List<AppointmentResponseDto> getAppointmentsByPatientId(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId));

        return patient.getAppointments()
                .stream()
                .map(appointment -> {
                    AppointmentResponseDto dto = new AppointmentResponseDto();
                    dto.setId(appointment.getId());
                    dto.setAppointmentTime(appointment.getAppointmentTime());
                    dto.setReason(appointment.getReason());

                    DoctorResponseDto doctorDto = new DoctorResponseDto(
                            appointment.getDoctor().getId(),
                            appointment.getDoctor().getName(),
                            appointment.getDoctor().getSpecialization(),
                            appointment.getDoctor().getEmial() // careful: typo in Doctor entity
                    );
                    dto.setDoctor(doctorDto);

                    return dto;
                })
                .collect(Collectors.toList());
    }
    public List<PatientResponseDto> getAllPatients(Integer pageNumber, Integer pageSize) {
        return patientRepository.findAllPatients(PageRequest.of(pageNumber, pageSize))
                .stream()
                .map(patient -> modelMapper.map(patient, PatientResponseDto.class))
                .collect(Collectors.toList());
    }
//    public List<PatientResponseDto> getAllPatients() {
//        return patientRepository.findAll()
//                .stream()
//                .map(patient -> {
//                    PatientResponseDto dto = new PatientResponseDto();
//                    dto.setId(patient.getId());
//                    dto.setName(patient.getName());
//                    dto.setGender(patient.getGender());
//                    dto.setBirthDate(patient.getBirthdate());
//                    dto.setBloodGroup(patient.getBloodgroup());
//                    return dto;
//                })
//                .collect(Collectors.toList());
//                }
    }

