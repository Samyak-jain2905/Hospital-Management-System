package com.samyaksProject.HospitalManagement.DTO;

import com.samyaksProject.HospitalManagement.entity.type.BloodGroupType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BloodGroupCountResponseEntity {
    private BloodGroupType bloodGroupType;
   private Long count;
}
