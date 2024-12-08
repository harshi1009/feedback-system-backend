package com.feedback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.feedback.model.Criteria;
import com.feedback.service.CriteriaService;

import java.util.List;

@RestController
@RequestMapping("/api/criteria")
@CrossOrigin(origins = "http://localhost:3000") // Update with your frontend URL
public class CriteriaController {

    @Autowired
    private CriteriaService criteriaService;

    @GetMapping
    public List<Criteria> getAllCriteria() {
        return criteriaService.getAllCriteria();
    }

    @PostMapping
    public Criteria addCriteria(@RequestBody Criteria criteria) {
        return criteriaService.addCriteria(criteria);
    }

    @PutMapping("/{id}")
    public Criteria updateCriteria(@PathVariable Long id, @RequestBody Criteria updatedCriteria) {
        return criteriaService.updateCriteria(id, updatedCriteria);
    }

    @DeleteMapping("/{id}")
    public void deleteCriteria(@PathVariable Long id) {
        criteriaService.deleteCriteria(id);
    }

    @PostMapping("/reorder")
    public void reorderCriteria(@RequestBody List<Criteria> reorderedList) {
        criteriaService.reorderCriteria(reorderedList);
    }
}

