package com.samyaksProject.HospitalManagement.Security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import static com.samyaksProject.HospitalManagement.entity.type.PermissionType.*;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class webSecurityConfig {
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthFilter jwtAuthFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final HandlerExceptionResolver handlerExceptionResolver;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(csrfConfig-> csrfConfig.disable())
                .sessionManagement(sessionConfig ->sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .formLogin(form -> form.disable())
//                .httpBasic(basic -> basic.disable())
        .authorizeHttpRequests(Auth -> Auth.requestMatchers("/auth/**", "/public/**").permitAll()
                      .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/admin/**")
                        .hasAnyAuthority(APPOINTMENT_DELETE.name(),USER_MANAGE.name())
                        .requestMatchers("/doctors/**").hasAnyRole("ADMIN", "DOCTOR")
                        .anyRequest()
                        .authenticated()
                )

                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oAuth2 -> oAuth2.failureHandler(
                    (request, response, exception) -> {
                        log.error("OAuth2 error :{} ",exception.getMessage(),exception);
                        response.sendRedirect("/login?error=" + exception.getMessage());
                        handlerExceptionResolver.resolveException(request,response,null,exception);



                    }
                )
                                .successHandler(oAuth2SuccessHandler)
                        )
                .exceptionHandling(exceptionHandlingConfigurer -> exceptionHandlingConfigurer.accessDeniedHandler((request, response, accessDeniedException) ->
                        {
                        handlerExceptionResolver.resolveException(request,response,null,accessDeniedException);
                        }
                        ) );
//        .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }


}
