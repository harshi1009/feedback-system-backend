package com.feedback.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feedback.model.AcademicYear;

public interface AcademicYearRepository extends JpaRepository<AcademicYear, Long> {
Optional<AcademicYear> findByYear(String year);
    
    // Find by year and semester
    Optional<AcademicYear> findByYearAndSemester(String year, String semester);
    AcademicYear findFirstByIsCurrentTrue();
    AcademicYear findFirstByEvaluationStatus(String evaluationStatus);
}

