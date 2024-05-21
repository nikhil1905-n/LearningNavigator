package com.nikhiln.learningNavigator.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private Long id;
    private String name;
    private List<Long> subjectIds;
    private List<Long> examIds;
    
}
