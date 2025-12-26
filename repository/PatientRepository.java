package com.samyaksProject.HospitalManagement.repository;

import com.samyaksProject.HospitalManagement.DTO.BloodGroupCountResponseEntity;
import com.samyaksProject.HospitalManagement.entity.Patient;
import com.samyaksProject.HospitalManagement.entity.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Patient findByName(String name);
    List<Patient> findByBirthdateOrEmail(LocalDate birthdate,String email);

    @Query("SELECT p From Patient p where p.bloodgroup=?1")
    List<Patient> findByBloodGroup(@Param("bloodgroup") BloodGroupType bloodGroup);
    @Query("SELECT p From Patient p where p.birthdate>:birthdate")
    List<Patient> findByBornAfterBirthdate(@Param("birthdate") LocalDate birthdate);

    @Query("SELECT p.bloodgroup, Count(p) from Patient p group by p.bloodgroup")
    List<Object[]> countSameBloodGroupPatient();

    @Query(value = "select * from patient",nativeQuery = true)
    List<Patient> findByAllThePatient();
@Transactional
    @Modifying
    @Query("UPDATE Patient p set p.name= :name where p.id=:id")
    int updateNameWithId(@Param("name") String name,@Param("id") Long id);

    @Query("SELECT new com.samyaksProject.HospitalManagement.DTO.BloodGroupCountResponseEntity(p.bloodgroup,"+" Count(p)) from Patient p group by p.bloodgroup")
    List<BloodGroupCountResponseEntity> bloodgroupcountentity();

    @Query(value = "select * from patient", nativeQuery = true)
    Page<Patient> findAllPatients(Pageable pageable);

    @Query(value = "select * from patient",nativeQuery = true)
    Page<Patient> findAllThePatient(Pageable pageable);

//@Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments a LEFT JOIN FETCH a.doctor")
@Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments")
    List<Patient> findAllPatientWithAppointment();

}
