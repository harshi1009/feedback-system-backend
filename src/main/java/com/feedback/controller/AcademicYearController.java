package com.feedback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.feedback.model.AcademicYear;
import com.feedback.service.AcademicYearService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/academic-years")
@CrossOrigin(origins = "http://localhost:3000")
public class AcademicYearController {

    @Autowired
    private AcademicYearService academicYearService;

    // Get all academic years
    @GetMapping
    public ResponseEntity<List<AcademicYear>> getAllAcademicYears() {
        List<AcademicYear> academicYears = academicYearService.getAllAcademicYears();
        return ResponseEntity.ok(academicYears);
    }

    // Create a new academic year
    @PostMapping
    public ResponseEntity<?> createAcademicYear(@Valid @RequestBody AcademicYear academicYear) {
        // Check if any required field is null or invalid
        if (academicYear == null || academicYear.getYear() == null || 
            academicYear.getSemester() == null || 
            academicYear.getSystemDefault() == null || 
            academicYear.getEvaluationStatus() == null) {

            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Missing or invalid fields! Ensure all fields (year, semester, systemDefault, evaluationStatus) are filled in correctly.");
        }

        try {
            AcademicYear createdYear = academicYearService.createAcademicYear(academicYear);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdYear); // Created status
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error creating academic year: " + e.getMessage());
        }
    }

    // Update an academic year by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAcademicYear(@PathVariable Long id, @Valid @RequestBody AcademicYear academicYear) {
        // Check if the ID exists
        if (!academicYearService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Academic year with ID " + id + " not found.");
        }

        // Validate required fields
        if (academicYear == null || academicYear.getYear() == null || 
            academicYear.getSemester() == null || 
            academicYear.getSystemDefault() == null || 
            academicYear.getEvaluationStatus() == null) {

            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Missing or invalid fields! Ensure all fields (year, semester, systemDefault, evaluationStatus) are filled in correctly.");
        }

        try {
            AcademicYear updatedYear = academicYearService.updateAcademicYear(id, academicYear);
            return ResponseEntity.ok(updatedYear); // Success status with updated entity
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error updating academic year: " + e.getMessage());
        }
    }

    // Delete an academic year by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAcademicYear(@PathVariable Long id) {
        if (!academicYearService.existsById(id)) { // Check if the ID exists
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Academic year with ID " + id + " not found.");
        }

        academicYearService.deleteAcademicYear(id);
        return ResponseEntity.noContent().build(); // No content status after deletion
    }
}
