package com.nikhiln.learningNavigator.service;

import java.util.List;

import com.nikhiln.learningNavigator.dto.StudentDto;

public interface StudentService {

    List<StudentDto> getStudents();
    StudentDto getStudentById(Long studentId);
    StudentDto registerStudent(StudentDto studentDto);
    StudentDto updateStudent(Long studentId, StudentDto studentDto);
    void deleteStudentById(Long studentId);

}
