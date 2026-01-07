package com.samyaksProject.HospitalManagement.DTO;

import lombok.Data;

@Data
public class OnBoardNewDoctorRequestDTO {
    private Long userId;
    private String specialization;
    private String name;

}
