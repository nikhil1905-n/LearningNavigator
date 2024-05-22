package com.nikhiln.learningNavigator.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import com.nikhiln.learningNavigator.service.EasterEggService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EasterEggController.class)
public class EasterEggControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EasterEggService easterEggService;

    @InjectMocks
    private EasterEggController easterEggController;

    @Test
    public void testGetRandomFactAboutNumber() throws Exception {
        int number = 42;
        String expectedResponse = "42 is the answer to life, the universe, and everything.";

        when(easterEggService.getRandomFactAboutNumber(number)).thenReturn(expectedResponse);

        mockMvc.perform(get("/hidden-feature/{number}", number))
               .andExpect(status().isOk())
               .andExpect(content().string(expectedResponse));
    }

}
