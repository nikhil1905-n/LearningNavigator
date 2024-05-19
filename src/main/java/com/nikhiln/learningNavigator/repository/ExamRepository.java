package com.nikhiln.learningNavigator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nikhiln.learningNavigator.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    
}
