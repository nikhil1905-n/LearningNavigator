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

import com.nikhiln.learningNavigator.dto.SubjectDto;
import com.nikhiln.learningNavigator.entity.Subject;
import com.nikhiln.learningNavigator.exception.NotFoundException;
import com.nikhiln.learningNavigator.repository.SubjectRepository;

public class SubjectServiceImplTest {

    @InjectMocks
    private SubjectServiceImpl subjectService;

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetSubjects() {
        Subject subject = new Subject();
        List<Subject> subjects = Collections.singletonList(subject);
        SubjectDto subjectDto = new SubjectDto();
        when(subjectRepository.findAll()).thenReturn(subjects);
        when(modelMapper.map(subject, SubjectDto.class)).thenReturn(subjectDto);

        List<SubjectDto> result = subjectService.getSubjects();

        assertEquals(1, result.size());
        assertEquals(subjectDto, result.get(0));
    }

    @Test
    public void testGetSubjectById() {
        Long subjectId = 1L;
        Subject subject = new Subject();
        SubjectDto subjectDto = new SubjectDto();
        when(subjectRepository.findById(subjectId)).thenReturn(Optional.of(subject));
        when(modelMapper.map(subject, SubjectDto.class)).thenReturn(subjectDto);

        SubjectDto result = subjectService.getSubjectById(subjectId);

        assertEquals(subjectDto, result);
    }

    @Test
    public void testGetSubjectById_NotFound() {
        Long subjectId = 1L;
        when(subjectRepository.findById(subjectId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> subjectService.getSubjectById(subjectId));
    }

}
