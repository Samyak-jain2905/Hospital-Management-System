package com.samyaksProject.HospitalManagement.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 50)
    private String name;

    @Column(length = 50)
    private String specialization;

    @Column(nullable = false,unique = true,length = 100)
    private String emial;

    @ManyToMany(mappedBy = "doctors")

    private Set<Department> departments=new HashSet<>();

    @OneToMany(mappedBy = "doctor")

    private List<Appointment> appointments=new ArrayList<>();

}
