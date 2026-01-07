package com.samyaksProject.HospitalManagement.service;

import com.samyaksProject.HospitalManagement.DTO.LoginRequestDto;
import com.samyaksProject.HospitalManagement.DTO.LoginResponseDto;

import com.samyaksProject.HospitalManagement.DTO.SignUpRequestDTO;
import com.samyaksProject.HospitalManagement.DTO.SignUpResponseDto;
import com.samyaksProject.HospitalManagement.Security.AuthUtil;
import com.samyaksProject.HospitalManagement.entity.Patient;
import com.samyaksProject.HospitalManagement.entity.User;
import com.samyaksProject.HospitalManagement.entity.type.AuthProviderType;
import com.samyaksProject.HospitalManagement.entity.type.RoleType;
import com.samyaksProject.HospitalManagement.repository.PatientRepository;
import com.samyaksProject.HospitalManagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PatientRepository patientRepository;
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword()));
    User user=(User) authentication.getPrincipal();
    String token=authUtil.generateAccessToken(user);
     return new LoginResponseDto(token, user.getId());
    }

    //login controller
    public User signUpInternal(SignUpRequestDTO signupRequestDto,AuthProviderType providerType,String providerId){
        User user =
                userRepository.findByUsername(signupRequestDto.getUsername())
                        .orElse(null);

        if (user != null) {
            throw new IllegalArgumentException("User already exists");
        }
       user=User.builder()
                .username(signupRequestDto.getUsername())
               .providerId(providerId)
               .providerType(providerType)
               .roles(signupRequestDto.getRoles()) //Set.of(RoleType.PATIENT)
                .build();
        if(providerType==AuthProviderType.EMAIL){
            user.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
        }
        user = userRepository.save(user);
        Patient patient=Patient.builder()
                        .name(signupRequestDto.getName())
                        .email(signupRequestDto.getUsername())
                        .user(user)
                        .build();
        patientRepository.save(patient);
        return user;
    }
    @PreAuthorize("permitAll()")
    public SignUpResponseDto signup(SignUpRequestDTO signupRequestDto) {
       User user=signUpInternal(signupRequestDto,AuthProviderType.EMAIL,null);
        return new SignUpResponseDto(user.getId(),user.getUsername());
    }

    @Transactional
    public ResponseEntity<LoginResponseDto> handleOAuth2loginrequest(OAuth2User oAuth2User, String registrationid) {
        //  fetch providerType and providerId
        // save the providertype and provider id info with user
        //if the user has an account : directly login
        // otherwise , first signup and then login
        AuthProviderType providerType=authUtil.getProviderTypeFromRegistrationId(registrationid);
        String providerId= authUtil.determineProviderIdFromOAuth2User(oAuth2User,registrationid);

        User user=userRepository.findByProviderIdAndProviderType(providerId,providerType).orElse(null);
        String email=oAuth2User.getAttribute("email");
        String name=oAuth2User.getAttribute("name");

        User emailUser=userRepository.findByUsername(email).orElse(null);

        if(user ==null && emailUser ==null){
            String username=authUtil.determinUsernameFromOAuth2User(oAuth2User,registrationid,providerId);
                user=signUpInternal(new SignUpRequestDTO(username,null,name,Set.of(RoleType.PATIENT)),providerType,providerId);
        }
        else if(user !=null){
            if(email !=null && !email.isBlank() && !email.equals(user.getUsername())){
                user.setUsername(email);
                userRepository.save(user);
            }
        }
else{
    throw new BadCredentialsException("this email is already registered with provider"+ emailUser.getProviderType());
        }
        LoginResponseDto loginResponseDto=new LoginResponseDto(authUtil.generateAccessToken(user),user.getId());
        return ResponseEntity.ok(loginResponseDto);
    }
}
