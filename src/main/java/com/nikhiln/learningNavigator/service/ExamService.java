package com.nikhiln.learningNavigator.service;

import java.util.List;

import com.nikhiln.learningNavigator.dto.ExamDto;

public interface ExamService {
    
    List<ExamDto> getExams();
    ExamDto getExamaById(Long examId);
    ExamDto creatExam(ExamDto examDto);
    void registerStudentToExam(Long examId, Long studentId);
    ExamDto updatExam(Long examId, ExamDto examDto);
    void deleteExamById(Long examId);

}
