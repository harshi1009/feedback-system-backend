package com.feedback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feedback.model.Subject;
import com.feedback.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    // Get all subjects
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    // Create a new subject
    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    // Update an existing subject
    public Subject updateSubject(Long id, Subject updatedSubject) {
        // Check if the subject exists
        if (subjectRepository.existsById(id)) {
            updatedSubject.setId(id); // Set the ID to ensure it's updated correctly
            return subjectRepository.save(updatedSubject);
        } else {
            throw new RuntimeException("Subject not found for id: " + id);
        }
    }

    // Delete a subject by ID
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }

    // Get a subject by ID
    public Optional<Subject> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }
}
