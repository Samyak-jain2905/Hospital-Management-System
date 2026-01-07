package com.samyaksProject.HospitalManagement.entity;

import com.samyaksProject.HospitalManagement.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@Table(

        uniqueConstraints={
                @UniqueConstraint(name="unique_patient_name_birthdate",columnNames = {"name","birthdate"})
},
        indexes = {
                @Index(name="idx_patient_birthdate",columnList = "birthdate")
        }

)
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private LocalDate birthdate;
    @ToString.Exclude
    @Column(unique = true,nullable = false)
    private String email;

    private String gender;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodgroup;

    @OneToOne()
    @MapsId()
    private User user;


//instead use @ToString
//    @Override
//    public String toString() {
//        return "Patient{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", birthdate=" + birthdate +
//                ", email='" + email + '\'' +
//                ", gender='" + gender + '\'' +
//                '}';
//    }
    @ToString.Exclude
    @OneToOne(cascade = {CascadeType.ALL},orphanRemoval = true)
    @JoinColumn(name="patient_insurance_id")
    private Insurance insurance;

    @ToString.Exclude
    @OneToMany(mappedBy = "patient",cascade = {CascadeType.REMOVE},orphanRemoval = true,fetch = FetchType.EAGER)
    List<Appointment> appointments;


}


