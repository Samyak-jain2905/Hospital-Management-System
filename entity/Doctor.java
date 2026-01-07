package com.samyaksProject.HospitalManagement.entity;



import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @MapsId
    private User user;

    @Column(nullable = false,length = 50)
    private String name;

    @Column(length = 50)
    private String specialization;

    @Column(unique = true,length = 100)
    private String email;



    @ManyToMany(mappedBy = "doctors")

    private Set<Department> departments=new HashSet<>();

    @OneToMany(mappedBy = "doctor")

    private List<Appointment> appointments=new ArrayList<>();

}
