package com.samyaksProject.HospitalManagement.service;

import com.samyaksProject.HospitalManagement.DTO.LoginRequestDto;
import com.samyaksProject.HospitalManagement.DTO.LoginResponseDto;

import com.samyaksProject.HospitalManagement.DTO.SignUpResponseDto;
import com.samyaksProject.HospitalManagement.Security.AuthUtil;
import com.samyaksProject.HospitalManagement.entity.User;
import com.samyaksProject.HospitalManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword()));
    User user=(User) authentication.getPrincipal();
    String token=authUtil.generateAccessToken(user);
     return new LoginResponseDto(token, user.getId());
    }

    @PreAuthorize("permitAll()")
    public SignUpResponseDto signup(LoginRequestDto signupRequestDto) {
        User existingUser =
                userRepository.findByUsername(signupRequestDto.getUsername())
                        .orElse(null);

        if (existingUser != null) {
            throw new IllegalArgumentException("User already exists");
        }
        User user=userRepository.save(User.builder()
                .username(signupRequestDto.getUsername())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .build());
        return new SignUpResponseDto(user.getId(),user.getUsername());
    }
}
