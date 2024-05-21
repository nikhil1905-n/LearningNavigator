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

import com.nikhiln.learningNavigator.dto.SubjectDto;
import com.nikhiln.learningNavigator.service.SubjectService;

@RestController
@RequestMapping(SubjectController.SUBJECT_API_ENDPOINT)
public class SubjectController {

    public static final String SUBJECT_API_ENDPOINT = "/subjects";
    public static final String SUBJECT_ID = "/{id}";

    @Autowired
    private SubjectService subjectService;


    @GetMapping
    public ResponseEntity<List<SubjectDto>> getSubjects() {

        return ResponseEntity.ok().body(subjectService.getSubjects());

    }

    @GetMapping(SUBJECT_ID)
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable Long id) {
    
        return ResponseEntity.ok().body(subjectService.getSubjectById(id));
    
    }

    @PostMapping
    public ResponseEntity<SubjectDto> createSubject(@RequestBody SubjectDto subjectDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.createSubject(subjectDto));
        
    }

    @PutMapping(SUBJECT_ID)
    public ResponseEntity<SubjectDto> updateSubject(@PathVariable Long id, @RequestBody SubjectDto subjectDto) {

        return ResponseEntity.ok().body(subjectService.updateSubject(id, subjectDto));
        
    }
    
    @DeleteMapping(SUBJECT_ID)
    public ResponseEntity<Void> deleteSubjectById(@PathVariable Long id) {

        subjectService.deleteSubjectById(id);
        return ResponseEntity.ok().build();
        
    }
    
}
