package com.ne.starter.controllers;

import com.ne.starter.dtos.requests.CreateStudentDto;
import com.ne.starter.dtos.requests.UpdateStudentDto;
import com.ne.starter.dtos.response.ApiResponse;
import com.ne.starter.exceptions.InternalServerErrorException;
import com.ne.starter.models.Student;
import com.ne.starter.services.IStudentService;
import com.ne.starter.utils.ExceptionUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/students")
@Validated
public class StudentController {
    private final IStudentService studentService;

    @Autowired
    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody @Valid CreateStudentDto dto ) {
        try{
        return ResponseEntity.ok().body(new ApiResponse(
                true,
                "Successfully created the user",
                studentService.registerStudent(dto)
        ));

        }
        catch (Exception e){
            throw new InternalServerErrorException(e.getMessage());
        }
//        try {
//            return ResponseEntity.ok(new ApiResponse(true, "Successfully registered", studentService.registerStudent(dto)));
//        }
//        catch (Exception e) {
//
//            return ExceptionUtils.handleControllerExceptions(e);
//        }
    }
    @GetMapping("/get-student-by-id/{studentId}")
    public ResponseEntity<ApiResponse> getStudentById(@PathVariable(value = "studentId") UUID studentId) {
        try{
            return ResponseEntity.ok(new ApiResponse(true, "Student retrieved successfully", studentService.getStudentById(studentId)));

        }
        catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }
//    @PutMapping("/update-student/{studentId}")
//    public ResponseEntity<ApiResponse> updateStudent(@PathVariable(value = "studentId") UUID studentId, @RequestBody @Valid UpdateStudentDto dto) {
//        try{
//            return ResponseEntity.ok(
//                    new ApiResponse(
//                            true,
//                            "Student updated successfully",
//                            studentService.updateStudent()
//
//                    )
//            )
//
//        }
//        catch(Exception e) {
//            return ExceptionUtils.handleControllerExceptions(e);
//        }
//    }

}
