package com.feedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feedback.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
