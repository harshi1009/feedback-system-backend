package com.feedback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feedback.model.DashboardData;
import com.feedback.service.DashboardService;

//DashboardController.java
@CrossOrigin(origins = "https://vamshikumar32501.github.io/student-feedback-frontend")
@RestController
@RequestMapping("/api/auth")
public class DashboardController {

 @Autowired
 private DashboardService dashboardService;

 @GetMapping("/admin/dashboard")
 public ResponseEntity<DashboardData> getDashboard() {
     DashboardData dashboardData = dashboardService.getDashboardData();
     return ResponseEntity.ok(dashboardData);
 }
}

