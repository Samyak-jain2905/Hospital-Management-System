package com.samyaksProject.HospitalManagement.service;

import com.samyaksProject.HospitalManagement.DTO.AppointmentResponseDto;
import com.samyaksProject.HospitalManagement.DTO.DoctorResponseDto;
import com.samyaksProject.HospitalManagement.entity.Appointment;
import com.samyaksProject.HospitalManagement.entity.Doctor;
import com.samyaksProject.HospitalManagement.entity.Patient;
import com.samyaksProject.HospitalManagement.repository.AppointmentRepository;
import com.samyaksProject.HospitalManagement.repository.DoctorRepository;
import com.samyaksProject.HospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class AppointmentService {
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;


    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long doctorid,Long patientid){

        Doctor doctor=doctorRepository.findById(doctorid).orElseThrow();
        Patient patient=patientRepository.findById(patientid).orElseThrow();

        if(appointment.getId()!=null) throw new IllegalArgumentException();

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointments().add(appointment); //for bidirectional consistancy

        return appointmentRepository.save(appointment);
    }
    @Transactional
    public Appointment reAssignAppiontmentToAnotherDoctor(Long appointmentId,Long doctorId){
       Appointment appointment=appointmentRepository.findById(appointmentId).orElseThrow();
       Doctor doctor=doctorRepository.findById(doctorId).orElseThrow();

       appointment.setDoctor(doctor);

      doctor.getAppointments().add(appointment);// bidirectional consistancy

        return appointment;
    }
    public List<AppointmentResponseDto> getAppointmentsByDoctorId(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + doctorId));

        return doctor.getAppointments()
                .stream()
                .map(appointment -> {
                    AppointmentResponseDto dto = new AppointmentResponseDto();
                    dto.setId(appointment.getId());
                    dto.setAppointmentTime(appointment.getAppointmentTime()); // assuming entity field is dateTime
                    dto.setReason(appointment.getReason());

                    // Fill doctor info
                    DoctorResponseDto doctorDto = new DoctorResponseDto();
                    doctorDto.setId(doctor.getId());
                    doctorDto.setName(doctor.getName());
                    doctorDto.setSpecialization(doctor.getSpecialization());
                    doctorDto.setEmail(doctor.getEmial()); // typo in entity is "emial", so use that getter!
                    dto.setDoctor(doctorDto);

                    return dto;
                })
                .collect(Collectors.toList());
    }
    }

