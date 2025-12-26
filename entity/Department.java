package com.samyaksProject.HospitalManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false,unique = true,length = 100)
    private String name;



    @OneToOne()
    private Doctor headDoctor;

    @ManyToMany()
    @JoinTable(
            name="my_dept_doctors",
            joinColumns = @JoinColumn(name="dept_id"),
            inverseJoinColumns = @JoinColumn(name="doctor_id")
    )
    private Set<Doctor> doctors=new HashSet<>();
}
