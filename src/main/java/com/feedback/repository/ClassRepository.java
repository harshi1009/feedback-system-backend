// src/main/java/com/feedback/repository/ClassRepository.java
package com.feedback.repository;

import com.feedback.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Class, Long> {
    // Additional query methods if needed
}
