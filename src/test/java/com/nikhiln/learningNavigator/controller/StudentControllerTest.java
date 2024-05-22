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
import com.nikhiln.learningNavigator.dto.StudentDto;
import com.nikhiln.learningNavigator.service.StudentService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetStudents() throws Exception {
        StudentDto student = new StudentDto(1L, "John Doe", Arrays.asList(1L, 2L), Arrays.asList(3L, 4L));
        when(studentService.getStudents()).thenReturn(Arrays.asList(student));

        mockMvc.perform(get("/students"))
               .andExpect(status().isOk())
               .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(student))));
    }

    @Test
    public void testRegisterStudent() throws Exception {
        StudentDto student = new StudentDto(1L, "John Doe", Arrays.asList(1L, 2L), Arrays.asList(3L, 4L));
        when(studentService.registerStudent(student)).thenReturn(student);

        mockMvc.perform(post("/students")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(student)))
               .andExpect(status().isCreated())
               .andExpect(content().json(objectMapper.writeValueAsString(student)));
    }

}
