package com.nikhiln.learningNavigator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nikhiln.learningNavigator.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    
}
