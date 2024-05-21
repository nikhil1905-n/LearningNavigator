package com.nikhiln.learningNavigator.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikhiln.learningNavigator.dto.StudentDto;
import com.nikhiln.learningNavigator.entity.Student;
import com.nikhiln.learningNavigator.exception.NotFoundException;
import com.nikhiln.learningNavigator.repository.ExamRepository;
import com.nikhiln.learningNavigator.repository.StudentRepository;
import com.nikhiln.learningNavigator.repository.SubjectRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<StudentDto> getStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(student -> modelMapper.map(student, StudentDto.class)).collect(Collectors.toList());
    }

    @Override
    public StudentDto getStudentById(Long studentId) {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException("Student with ID " + studentId + " Not Found"));

        // Optional<Student> student = studentRepository.findById(studentId);

        // if(student.isEmpty()) {
        //     throw new NotFoundException("Student with ID " + studentId + " Not Found");
        // }

        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto registerStudent(StudentDto studentDto) {

        Student student = modelMapper.map(studentDto, Student.class);

        Student savedStudent = studentRepository.save(student);

        return modelMapper.map(savedStudent, StudentDto.class);

    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto studentDto) {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException("Student with ID " + studentId + " Not Found"));

        // OR

        // Optional<Student> student = studentRepository.findById(studentId);

        // if(student.isEmpty()) {
        //     throw new NotFoundException("Student with ID " + studentId + " Not Found");
        // }

        student.setName(studentDto.getName());
        student.setSubjects(subjectRepository.findAllById(studentDto.getSubjectIds()));
        student.setExams(examRepository.findAllById(studentDto.getExamIds()));

        Student updatedStudent = studentRepository.save(student);

        return modelMapper.map(updatedStudent, StudentDto.class);

    }

    @Override
    public void deleteStudentById(Long studentId) {

        Optional<Student> student = studentRepository.findById(studentId);

        if(student.isEmpty()) {
            throw new NotFoundException("Student with ID " + studentId + " Not Found");
        }

        studentRepository.deleteById(studentId);
        
    }
    
}
