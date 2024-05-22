package com.nikhiln.learningNavigator.service;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.nikhiln.learningNavigator.dto.StudentDto;
import com.nikhiln.learningNavigator.entity.Student;
import com.nikhiln.learningNavigator.exception.NotFoundException;
import com.nikhiln.learningNavigator.repository.StudentRepository;

public class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetStudents() {
        Student student = new Student();
        List<Student> students = Collections.singletonList(student);
        StudentDto studentDto = new StudentDto();
        when(studentRepository.findAll()).thenReturn(students);
        when(modelMapper.map(student, StudentDto.class)).thenReturn(studentDto);

        List<StudentDto> result = studentService.getStudents();

        assertEquals(1, result.size());
        assertEquals(studentDto, result.get(0));
    }

    @Test
    public void testGetStudentById() {
        Long studentId = 1L;
        Student student = new Student();
        StudentDto studentDto = new StudentDto();
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(modelMapper.map(student, StudentDto.class)).thenReturn(studentDto);

        StudentDto result = studentService.getStudentById(studentId);

        assertEquals(studentDto, result);
    }

    @Test
    public void testGetStudentById_NotFound() {
        Long studentId = 1L;
        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> studentService.getStudentById(studentId));
    }

}
