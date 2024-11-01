package com.feedback.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feedback.model.Criteria;
import com.feedback.repository.CriteriaRepository;

import java.util.List;

@Service
public class CriteriaService {
 @Autowired
 private CriteriaRepository criteriaRepository;

 public List<Criteria> getAllCriteria() {
     return criteriaRepository.findAll();
 }

 public Criteria saveCriteria(Criteria criteria) {
     return criteriaRepository.save(criteria);
 }
}
