// src/main/java/com/feedback/service/ClassService.java
package com.feedback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feedback.model.Class; // Ensure the name matches your model class
import com.feedback.repository.ClassRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    public Class addClass(Class newClass) {
        return classRepository.save(newClass);
    }

    public void deleteClass(Long id) {
        classRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return classRepository.existsById(id); // Check if the class exists
    }

    public Class updateClass(Long id, Class updatedClass) {
        if (!existsById(id)) {
            throw new RuntimeException("Class not found"); // Handle this appropriately
        }
        updatedClass.setId(id); // Set the ID for the update
        return classRepository.save(updatedClass);
    }
}
