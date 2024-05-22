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

import com.nikhiln.learningNavigator.dto.ExamDto;
import com.nikhiln.learningNavigator.entity.Exam;
import com.nikhiln.learningNavigator.exception.NotFoundException;
import com.nikhiln.learningNavigator.repository.ExamRepository;

public class ExamServiceImplTest {

    @InjectMocks
    private ExamServiceImpl examService;

    @Mock
    private ExamRepository examRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetExams() {
        Exam exam = new Exam();
        List<Exam> exams = Collections.singletonList(exam);
        ExamDto examDto = new ExamDto();
        when(examRepository.findAll()).thenReturn(exams);
        when(modelMapper.map(exam, ExamDto.class)).thenReturn(examDto);

        List<ExamDto> result = examService.getExams();

        assertEquals(1, result.size());
        assertEquals(examDto, result.get(0));
    }

    @Test
    public void testGetExamaById() {
        Long examId = 1L;
        Exam exam = new Exam();
        ExamDto examDto = new ExamDto();
        when(examRepository.findById(examId)).thenReturn(Optional.of(exam));
        when(modelMapper.map(exam, ExamDto.class)).thenReturn(examDto);

        ExamDto result = examService.getExamaById(examId);

        assertEquals(examDto, result);
    }

    @Test
    public void testGetExamaById_NotFound() {
        Long examId = 1L;
        when(examRepository.findById(examId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> examService.getExamaById(examId));
    }

}
