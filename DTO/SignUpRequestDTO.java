package com.samyaksProject.HospitalManagement.DTO;

import com.samyaksProject.HospitalManagement.entity.type.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDTO {
    private String username;
    private String password;
    private String name;

    Set<RoleType> roles=new HashSet<>();

}
