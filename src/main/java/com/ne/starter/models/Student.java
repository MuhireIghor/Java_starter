package com.ne.starter.models;

import com.ne.starter.enums.EGender;
import jakarta.persistence.*;
import lombok.*;

import java.net.DatagramSocket;
import java.util.Date;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
public class Student extends Person {

private String className;
@OneToOne
@JoinColumn(name = "student_profile")
private User profile;

public Student(String firstName,String lastName, String email, EGender gender, String password, String className) {
    super(firstName,lastName,email,password,gender);
    this.className = className;

}

}
