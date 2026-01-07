package com.samyaksProject.HospitalManagement.service;




import com.samyaksProject.HospitalManagement.DTO.DoctorResponseDto;
import com.samyaksProject.HospitalManagement.DTO.OnBoardNewDoctorRequestDTO;
import com.samyaksProject.HospitalManagement.entity.Doctor;
import com.samyaksProject.HospitalManagement.entity.User;
import com.samyaksProject.HospitalManagement.entity.type.RoleType;
import com.samyaksProject.HospitalManagement.repository.DoctorRepository;
import com.samyaksProject.HospitalManagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;


    public List<DoctorResponseDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doctor -> modelMapper.map(doctor, DoctorResponseDto.class))
                .collect(Collectors.toList());
    }


    @Transactional
    public DoctorResponseDto onBoardNewDoctor(OnBoardNewDoctorRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow();

        if(doctorRepository.existsById(dto.getUserId())) {
            throw new IllegalArgumentException("Already a doctor");
        }

        Doctor doctor = Doctor.builder()
                .name(dto.getName())
                .specialization(dto.getSpecialization())
                .user(user)
                .build();

        user.getRoles().add(RoleType.DOCTOR);

        return modelMapper.map(doctorRepository.save(doctor), DoctorResponseDto.class);
    }
}