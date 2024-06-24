package com.ne.starter.serviceImpl;

import com.ne.starter.dtos.requests.CreateRoleDto;
import com.ne.starter.dtos.requests.CreateStudentDto;
import com.ne.starter.dtos.requests.CreateUserDto;
import com.ne.starter.dtos.requests.UpdateStudentDto;
import com.ne.starter.enums.ERole;
import com.ne.starter.exceptions.BadRequestAlertException;
import com.ne.starter.exceptions.InternalServerErrorException;
import com.ne.starter.exceptions.NotFoundException;
import com.ne.starter.models.Role;
import com.ne.starter.models.Student;
import com.ne.starter.models.User;
import com.ne.starter.repositories.IStudentRepository;
import com.ne.starter.services.IStudentService;
import com.ne.starter.services.IUserService;
import com.ne.starter.utils.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private IStudentRepository studentRepository;
    @Autowired
    private IUserService userService;

    @Override
    public Student registerStudent(CreateStudentDto dto) {
        try {
            CreateUserDto createUserDto = new CreateUserDto(
                    dto.getFirstName(),
                    dto.getLastName(),
                    dto.getEmail(),
                    dto.getPassword()
            );
            User profile = userService.createUser(createUserDto, ERole.STUDENT);
            Student student = new Student();
            student.setGender(dto.getGender());
            student.setEmail(dto.getEmail());
            student.setFirstName(dto.getFirstName());
            student.setLastName(dto.getLastName());
            student.setClassName(dto.getClassName());
            student.setPassword(HashUtil.hashPassword(dto.getPassword()));
            student.setProfile(profile);
            System.out.println("done2==========");

            return studentRepository.save(student);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @Override
    public Student updateStudent(UUID id, UpdateStudentDto dto) {
        Student studentToUpdate = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student not found with id: " + id));
        studentToUpdate.setEmail(dto.getEmail());
        return studentToUpdate;
        //I want to update the given fields and the others remain as they were


    }

    @Override
    public Student getStudentById(UUID id) {
        return null;
    }

    @Override
    public List<Student> getAllStudents() {
        return List.of();
    }

    @Override
    public Student getStudentByEmail(String email) {
        return null;
    }

    @Override
    public void deleteStudent(UUID id) {

    }
}
