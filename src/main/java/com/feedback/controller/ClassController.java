package com.feedback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.feedback.model.Class; // Ensure the name matches your model
import com.feedback.service.ClassService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin(origins = "http://localhost:3000")
public class ClassController {

    @Autowired
    private ClassService classService;

    // Get all classes
    @GetMapping
    public ResponseEntity<List<Class>> getAllClasses() {
        List<Class> classes = classService.getAllClasses();
        return ResponseEntity.ok(classes);
    }

    // Create a new class
    @PostMapping
    public ResponseEntity<?> addClass(@Valid @RequestBody Class newClass) {
        if (newClass == null || newClass.getClassName() == null) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Missing or invalid fields! Ensure all fields (className) are filled in correctly.");
        }

        try {
            Class createdClass = classService.addClass(newClass);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdClass);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error creating class: " + e.getMessage());
        }
    }

    // Update a class by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateClass(@PathVariable Long id, @Valid @RequestBody Class updatedClass) {
        if (!classService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Class with ID " + id + " not found.");
        }

        if (updatedClass == null || updatedClass.getClassName() == null) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Missing or invalid fields! Ensure all fields (className) are filled in correctly.");
        }

        try {
            Class classEntity = classService.updateClass(id, updatedClass);
            return ResponseEntity.ok(classEntity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error updating class: " + e.getMessage());
        }
    }

    // Delete a class by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        if (!classService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Corrected here
        }

        classService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }
}
