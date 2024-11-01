package com.feedback.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feedback.model.AcademicYear;
import com.feedback.repository.AcademicYearRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AcademicYearService {

    @Autowired
    private AcademicYearRepository academicYearRepository;

    // Fetch all academic years
    public List<AcademicYear> getAllAcademicYears() {
        return academicYearRepository.findAll();
    }

    // Fetch a single academic year by ID
    public Optional<AcademicYear> getAcademicYearById(Long id) {
        return academicYearRepository.findById(id);
    }

    // Create a new academic year
    @Transactional
    public AcademicYear createAcademicYear(AcademicYear academicYear) {
        // You can add some validation logic here before saving
        return academicYearRepository.save(academicYear);
    }

    // Update an academic year
    @Transactional
    public AcademicYear updateAcademicYear(Long id, AcademicYear updatedYear) {
        // Check if the academic year exists
        AcademicYear existingYear = academicYearRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic year with ID " + id + " not found."));

        // Update the fields
        existingYear.setYear(updatedYear.getYear());
        existingYear.setSemester(updatedYear.getSemester());
        existingYear.setSystemDefault(updatedYear.getSystemDefault());
        existingYear.setEvaluationStatus(updatedYear.getEvaluationStatus());

        // Save the updated academic year
        return academicYearRepository.save(existingYear);
    }

    // Delete an academic year by ID
    @Transactional
    public void deleteAcademicYear(Long id) {
        academicYearRepository.deleteById(id);
    }

    // Check if an academic year exists by ID
    public boolean existsById(Long id) {
        return academicYearRepository.existsById(id);
    }
}
