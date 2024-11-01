// src/main/java/com/feedback/model/Class.java
package com.feedback.model;

import jakarta.persistence.*;

@Entity
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String className;

    @ManyToOne
    private AcademicYear academicYear; // Link to Academic Year

    // Constructors, getters and setters
    public Class() {}

    public Class(String className, AcademicYear academicYear) {
        this.className = className;
        this.academicYear = academicYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }
}
