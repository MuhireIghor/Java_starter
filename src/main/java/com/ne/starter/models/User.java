package com.ne.starter.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ne.starter.audits.TimestampAudit;
import com.ne.starter.enums.EGender;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Timer;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column(name = "email",unique = true,nullable = false)
    private String email;
    @JsonIgnore
    private String password;
    @Enumerated(EnumType.STRING)
    private EGender gender;


    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    public User(String firstName, String lastName, String email, String password,Role role,EGender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.gender = gender;
    }
    public User(String firstName, String lastName, String email, String password,EGender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;

    }


}
