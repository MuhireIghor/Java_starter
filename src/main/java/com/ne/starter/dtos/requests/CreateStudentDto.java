package com.ne.starter.dtos.requests;

import com.ne.starter.enums.EGender;
import com.ne.starter.enums.ERole;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentDto {

private String firstName;
private String lastName;
@Email
private String email;
private String password;
private String className;
private EGender gender;
private Date birthDate;
private ERole roleName;


}
