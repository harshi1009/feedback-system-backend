package com.feedback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.feedback.model.Criteria;
import com.feedback.service.CriteriaService;

import java.util.List;

@RestController
@RequestMapping("/api/evaluation/criteria")
@CrossOrigin(origins = "http://localhost:3000") // Adjust if needed
public class CriteriaController {
 @Autowired
 private CriteriaService criteriaService;

 @GetMapping
 public List<Criteria> getAllCriteria() {
     return criteriaService.getAllCriteria();
 }

 @PostMapping
 public Criteria saveCriteria(@RequestBody Criteria criteria) {
     return criteriaService.saveCriteria(criteria);
 }
}
