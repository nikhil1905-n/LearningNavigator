package com.nikhiln.learningNavigator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikhiln.learningNavigator.dto.StudentDto;
import com.nikhiln.learningNavigator.service.StudentService;

@RestController
@RequestMapping(StudentController.STUDENT_API_ENDPOINT)
public class StudentController {

    public static final String STUDENT_API_ENDPOINT = "/students";
    public static final String STUDENT_ID = "/{id}";

    @Autowired
    private StudentService studentService;


    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudents() {

        return ResponseEntity.ok().body(studentService.getStudents());

    }

    @GetMapping(STUDENT_ID)
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {

        return ResponseEntity.ok().body(studentService.getStudentById(id));

    }

    @PostMapping
    public ResponseEntity<StudentDto> registerStudent(@RequestBody StudentDto studentDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.registerStudent(studentDto));
        
    }

    @PutMapping(STUDENT_ID)
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) {

        return ResponseEntity.ok().body(studentService.updateStudent(id, studentDto));
        
    }
    
    @DeleteMapping(STUDENT_ID)
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id) {

        studentService.deleteStudentById(id);
        return ResponseEntity.ok().build();
        
    }
    
}
