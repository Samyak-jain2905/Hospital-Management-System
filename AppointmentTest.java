package com.samyaksProject.HospitalManagement;

import com.samyaksProject.HospitalManagement.entity.Appointment;
import com.samyaksProject.HospitalManagement.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class AppointmentTest {
    @Autowired

    AppointmentService appointmentService;
    @Test
    public void testCreateAppointment(){
         Appointment appointment=Appointment.builder()
                 .appointmentTime(LocalDateTime.of(2025,11,1,14,00,00))
                 .reason("cancer")
                 .build();
        var newAppointment = appointmentService.createNewAppointment(appointment,1L,2L);
        System.out.println(">>> Appointment saved successfully");
        System.out.println(newAppointment);

       var updatedAppointment= appointmentService.reAssignAppiontmentToAnotherDoctor(newAppointment.getId(), 3L);
        System.out.println(updatedAppointment);
    }
}
