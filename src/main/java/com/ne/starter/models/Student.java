package com.ne.starter.models;

import com.ne.starter.enums.EGender;
import jakarta.persistence.*;

import java.net.DatagramSocket;
import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, name = "first_name")
    private String firstName;
    @Column(nullable = false, name = "last_name")
    private String lastName;
    @Column(name = "dob")
    private Date dateOfBirth;
    @Enumerated(EnumType.STRING)
    private EGender gender;
    private String email;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

}
