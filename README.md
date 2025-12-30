A Hospital Management System (HMS) built using Spring Boot that streamlines hospital operations such as patient management, doctor scheduling, appointments, and secure access control.
The application uses Spring Security with JWT for stateless authentication and OAuth2 Login for social login support (e.g., Google).

 Features - 
 Doctor Management
 Patient Management
 Appointment Scheduling
 Department Management


 Role-Based Access Control (Admin, Doctor,     Patient)
 JWT Authentication (Stateless)
 OAuth2 Login (Google, GitHub, etc.)
 Secure REST APIs
 Scalable and modular architecture


 Tech Stack -
Backend
Java 17+
Spring Boot
Spring Security
JWT (JSON Web Tokens)
OAuth2 Client
Spring Data JPA
Hibernate
Database
MySQL

Build Tool -
Maven


Authentication & Security -
JWT Authentication
Stateless authentication using access tokens
Secure token generation and validation
Refresh token support (optional)
Password encryption using BCrypt
OAuth2 Login
Social login using:
Google
GitHub (optional)
Automatic user registration on first login
Seamless integration with JWT for API access