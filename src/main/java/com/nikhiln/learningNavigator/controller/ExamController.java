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

import com.nikhiln.learningNavigator.dto.ExamDto;
import com.nikhiln.learningNavigator.service.ExamService;

@RestController
@RequestMapping(ExamController.EXAM_API_ENDPOINT)
public class ExamController {

    public static final String EXAM_API_ENDPOINT = "/exams";
    public static final String EXAM_ID = "/{id}";

    @Autowired
    private ExamService examService;


    @GetMapping
    public ResponseEntity<List<ExamDto>> getExams() {

        return ResponseEntity.ok().body(examService.getExams());

    }

    @GetMapping(EXAM_ID)
    public ResponseEntity<ExamDto> getExamaById(@PathVariable Long id) {
    
        return ResponseEntity.ok().body(examService.getExamaById(id));
    
    }

    @PostMapping
    public ResponseEntity<ExamDto> creatExam(@RequestBody ExamDto examDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(examService.creatExam(examDto));
        
    }

    @PostMapping(EXAM_ID + "/register/{studentId}")
    public ResponseEntity<Void> registerStudentToExam(@PathVariable(name = "id") Long examId, @PathVariable Long studentId) {

        examService.registerStudentToExam(examId, studentId);

        return ResponseEntity.ok().build();
        
    }

    @PutMapping(EXAM_ID)
    public ResponseEntity<ExamDto> updatExam(@PathVariable Long id, ExamDto examDto) {

        return ResponseEntity.ok().body(examService.updatExam(id, examDto));
        
    }
    
    @DeleteMapping(EXAM_ID)
    public ResponseEntity<Void> deleteExamById(@PathVariable Long id) {

        examService.deleteExamById(id);
        return ResponseEntity.ok().build();
        
    }
    
}
