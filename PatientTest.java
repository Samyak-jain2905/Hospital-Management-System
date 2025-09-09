package com.samyaksProject.HospitalManagement;

import com.samyaksProject.HospitalManagement.DTO.BloodGroupCountResponseEntity;
import com.samyaksProject.HospitalManagement.entity.Patient;
import com.samyaksProject.HospitalManagement.entity.type.BloodGroupType;
import com.samyaksProject.HospitalManagement.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PatientTest {
    @Autowired
    private PatientRepository patientRepository;
    @Test
    public void testPatientRepository(){
        List<Patient> list=patientRepository.findAllPatientWithAppointment();
        System.out.println(list);
    }
    @Test
    public void testPatientRepository2(){
        Patient patient = patientRepository.findByName("Amit Sharma");
        System.out.println(patient);
//        List<Patient> list=patientRepository.findByBirthdateOrEmail(LocalDate.of(2001,1,25),"sneha.gupta@example.com");
// for(Patient patient1:list){
//     System.out.println(patient1);
// }
//    }
//        List<Patient> list=patientRepository.findByBloodGroup(BloodGroupType.O_POSITIVE);
//        for(Patient patient2:list){
//            System.out.println(patient2);
//        }
//        List<Patient> list=patientRepository.findByBornAfterBirthdate(LocalDate.of(1900,10,10));
//        for(Patient patient3:list){
//            System.out.println(patient3);
//        }
//        List<Object[]> bloodGroupList=patientRepository.countSameBloodGroupPatient();
//        for(Object[] objects:bloodGroupList){
//            System.out.println(objects[0]+" "+objects[1]);
//        }
//        List<Patient> list=patientRepository.findByAllThePatient();
//        for(Patient patient4:list){
//            System.out.println(patient4);
//        }
//        int rowsAffected=patientRepository.updateNameWithId("maran hatel",5L);
//        System.out.println(rowsAffected);

//        List<BloodGroupCountResponseEntity> bloodGroupCountResponseEntities=patientRepository.bloodgroupcountentity();
//        for(BloodGroupCountResponseEntity bloodGroupCountResponse: bloodGroupCountResponseEntities){
//            System.out.println(bloodGroupCountResponseEntities);

        //}
        Page<Patient> list=patientRepository.findAllThePatient(PageRequest.of(2,2));
     for(Patient patient5:list){
            System.out.println(patient5);
        }

    }

}
