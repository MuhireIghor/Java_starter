package com.ne.starter.services;

import com.ne.starter.dtos.requests.CreateStudentDto;
import com.ne.starter.dtos.requests.UpdateStudentDto;
import com.ne.starter.models.Student;

import java.util.List;
import java.util.UUID;

public interface IStudentService {
    Student registerStudent(CreateStudentDto dto);
    Student updateStudent(UUID id,UpdateStudentDto dto);
    Student getStudentById(UUID id);
    List<Student> getAllStudents();
    Student getStudentByEmail(String email);
    void deleteStudent(UUID id);

}
