package com.nikhiln.learningNavigator.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikhiln.learningNavigator.dto.SubjectDto;
import com.nikhiln.learningNavigator.entity.Subject;
import com.nikhiln.learningNavigator.exception.NotFoundException;
import com.nikhiln.learningNavigator.repository.SubjectRepository;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SubjectDto> getSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        return subjects.stream().map(subject -> modelMapper.map(subject, SubjectDto.class)).collect(Collectors.toList());
    }

    @Override
    public SubjectDto getSubjectById(Long subjectId) {

        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new NotFoundException("Subject with ID " + subjectId + " Not Found"));

        // OR

        // Optional<Subject> subject = subjectRepository.findById(subjectId);

        // if(subject.isEmpty()) {
        //     throw new NotFoundException("Subject with ID " + subjectId + " Not Found");
        // }

        return modelMapper.map(subject, SubjectDto.class);
    }

    @Override
    public SubjectDto createSubject(SubjectDto subjectDto) {

        Subject subject = modelMapper.map(subjectDto, Subject.class);

        Subject savedSubject = subjectRepository.save(subject);

        return modelMapper.map(savedSubject, SubjectDto.class);

    }

    @Override
    public SubjectDto updateSubject(Long subjectId, SubjectDto subjectDto) {

        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new NotFoundException("Subject with ID " + subjectId + " Not Found"));

        // OR

        // Optional<Subject> subject = subjectRepository.findById(subjectId);

        // if(subject.isEmpty()) {
        //     throw new NotFoundException("Subject with ID " + subjectId + " Not Found");
        // }

        subject.setName(subjectDto.getName());

        Subject updatedSubject = subjectRepository.save(subject);

        return modelMapper.map(updatedSubject, SubjectDto.class);

    }

    @Override
    public void deleteSubjectById(Long subjectId) {

        Optional<Subject> subject = subjectRepository.findById(subjectId);

        if(subject.isEmpty()) {
            throw new NotFoundException("Subject with ID " + subjectId + " Not Found");
        }

        subjectRepository.deleteById(subjectId);
    }
    
}
