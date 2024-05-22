package com.nikhiln.learningNavigator.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikhiln.learningNavigator.dto.ExamDto;
import com.nikhiln.learningNavigator.service.ExamService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ExamController.class)
public class ExamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ExamService examService;

    @InjectMocks
    private ExamController examController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetExams() throws Exception {
        ExamDto exam = new ExamDto(1L, 1L, Arrays.asList(1L, 2L));
        when(examService.getExams()).thenReturn(Arrays.asList(exam));

        mockMvc.perform(get("/exams"))
               .andExpect(status().isOk())
               .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(exam))));
    }

    @Test
    public void testCreateExam() throws Exception {
        ExamDto exam = new ExamDto(1L, 1L, Arrays.asList(1L, 2L));
        when(examService.creatExam(exam)).thenReturn(exam);

        mockMvc.perform(post("/exams")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(exam)))
               .andExpect(status().isCreated())
               .andExpect(content().json(objectMapper.writeValueAsString(exam)));
    }
    
}
