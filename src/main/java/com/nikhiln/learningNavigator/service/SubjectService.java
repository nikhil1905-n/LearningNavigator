package com.nikhiln.learningNavigator.service;

import java.util.List;

import com.nikhiln.learningNavigator.dto.SubjectDto;

public interface SubjectService {

    List<SubjectDto> getSubjects();
    SubjectDto getSubjectById(Long subjectId);
    SubjectDto createSubject(SubjectDto subjectDto);
    SubjectDto updateSubject(Long subjectId, SubjectDto subjectDto);
    void deleteSubjectById(Long subjectId);

}
