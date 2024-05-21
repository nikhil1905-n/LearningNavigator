package com.nikhiln.learningNavigator.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikhiln.learningNavigator.dto.ExamDto;
import com.nikhiln.learningNavigator.entity.Exam;
import com.nikhiln.learningNavigator.entity.Student;
import com.nikhiln.learningNavigator.exception.NotFoundException;
import com.nikhiln.learningNavigator.repository.ExamRepository;
import com.nikhiln.learningNavigator.repository.StudentRepository;
import com.nikhiln.learningNavigator.repository.SubjectRepository;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ExamDto> getExams() {
        List<Exam> exams = examRepository.findAll();
        return exams.stream().map(exam -> modelMapper.map(exam, ExamDto.class)).collect(Collectors.toList());
        // return exams.stream().map(exam -> modelMapper(exam, ExamDto.class)).collect(Collectors.toList());
    }

    @Override
    public ExamDto getExamaById(Long examId) {

        Exam exam = examRepository.findById(examId).orElseThrow(() -> new NotFoundException("Exam with ID " + examId + " Not Found"));
        
        // OR

        // Optional<Exam> exam = examRepository.findById(examId);

        // if(exam.isEmpty()) {
        //     throw new NotFoundException("Exam with ID " + examId + " Not Found");
        // }

        return modelMapper.map(exam, ExamDto.class);

    }

    @Override
    public ExamDto creatExam(ExamDto examDto) {

        Exam exam = modelMapper.map(examDto, Exam.class);

        exam.setSubject(subjectRepository.findById(examDto.getSubjectId()).orElseThrow(() -> new NotFoundException("Subject with ID " + examDto.getSubjectId() + " Not Found")));
        exam.setStudents(studentRepository.findAllById(examDto.getStudentIds()));
        
        Exam savedExam = examRepository.save(exam);

        return modelMapper.map(savedExam, ExamDto.class);

    }


    @Override
    public void registerStudentToExam(Long examId, Long studentId) {

        Exam exam = examRepository.findById(examId).orElseThrow(() -> new NotFoundException("Exam with ID " + examId + " Not Found"));
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException("Student with ID " + examId + " Not Found"));
        
        if(!student.getSubjects().contains(exam.getSubject())) {
            throw new NotFoundException("Student must be enrolled in the subject to register for the exam");
        }

        exam.getStudents().add(student);
        
        examRepository.save(exam);
    }
    

    @Override
    public ExamDto updatExam(Long examId, ExamDto examDto) {

        Exam exam = examRepository.findById(examId).orElseThrow(() -> new NotFoundException("Exam with ID " + examId + " Not Found"));
        
        // OR

        // Optional<Exam> exam = examRepository.findById(examId);

        // if(exam.isEmpty()) {
        //     throw new NotFoundException("Exam with ID " + examId + " Not Found");
        // }

        exam.setSubject(subjectRepository.findById(examDto.getSubjectId()).orElseThrow(() -> new NotFoundException("Subject with ID " + examDto.getSubjectId() + " Not Found")));
        exam.setStudents(studentRepository.findAllById(examDto.getStudentIds()));

        examRepository.save(exam);

        return modelMapper.map(exam, ExamDto.class);

    }

    @Override
    public void deleteExamById(Long examId) {

        Optional<Exam> exam = examRepository.findById(examId);

        if(exam.isEmpty()) {
            throw new NotFoundException("Exam with ID " + examId + " Not Found");
        }

        examRepository.deleteById(examId);
        
    }

    
    
}
