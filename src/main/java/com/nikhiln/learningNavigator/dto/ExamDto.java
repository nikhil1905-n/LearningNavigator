package com.nikhiln.learningNavigator.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamDto {

    private Long id;
    private Long subjectId;
    private List<Long> studentIds;

}
