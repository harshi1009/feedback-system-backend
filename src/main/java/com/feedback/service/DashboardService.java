package com.feedback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feedback.model.AcademicYear;
import com.feedback.model.DashboardData;
import com.feedback.repository.AcademicYearRepository;
import com.feedback.repository.ClassRepository;
import com.feedback.repository.UserRepo; // Adjusted: using UserRepo to count students and faculties

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DashboardService {

    private static final Logger logger = LoggerFactory.getLogger(DashboardService.class);

    @Autowired
    private UserRepo userRepository; // Using UserRepo to fetch counts for student and faculty

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private AcademicYearRepository academicYearRepository;

    public DashboardData getDashboardData() {
        long totalFaculties = userRepository.countByUserType("faculty"); // Counting faculties from UserRepo
        long totalStudents = userRepository.countByUserType("student"); // Counting students from UserRepo
        long totalUsers = userRepository.count(); // Total users, includes all types
        long totalClasses = classRepository.count(); // Total classes

        // Log the values to verify if the counts are fetched correctly
        logger.debug("Total Faculties: " + totalFaculties);
        logger.debug("Total Students: " + totalStudents);
        logger.debug("Total Users: " + totalUsers);
        logger.debug("Total Classes: " + totalClasses);

        // Fetching academic year details
        AcademicYear currentAcademicYear = academicYearRepository.findFirstByEvaluationStatus("on-going");

        String academicYear = currentAcademicYear != null ? currentAcademicYear.getYear() : "N/A";
        String semester = currentAcademicYear != null ? currentAcademicYear.getSemester() : "N/A";
        String evaluationStatus = currentAcademicYear != null ? currentAcademicYear.getEvaluationStatus() : "N/A";

        return new DashboardData(totalFaculties, totalStudents, totalUsers, totalClasses, academicYear, semester, evaluationStatus);
    }
}
