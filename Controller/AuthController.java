package com.samyaksProject.HospitalManagement.Controller;

import com.samyaksProject.HospitalManagement.DTO.LoginRequestDto;
import com.samyaksProject.HospitalManagement.DTO.LoginResponseDto;
import com.samyaksProject.HospitalManagement.DTO.SignUpRequestDTO;
import com.samyaksProject.HospitalManagement.DTO.SignUpResponseDto;
import com.samyaksProject.HospitalManagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
      return ResponseEntity.ok(authService.login(loginRequestDto));
    }
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signup(@RequestBody SignUpRequestDTO signupRequestDto){
        return ResponseEntity.ok(authService.signup(signupRequestDto));
    }
}
