package com.feedback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.feedback.model.Question;
import com.feedback.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/api/evaluation/questions")
@CrossOrigin(origins = "http://localhost:3000") // Adjust if needed
public class QuestionController {
 @Autowired
 private QuestionService questionService;

 @GetMapping
 public List<Question> getQuestionsByCriteriaId(@RequestParam Long criteriaId) {
     return questionService.getQuestionsByCriteriaId(criteriaId);
 }

 @PostMapping
 public Question saveQuestion(@RequestBody Question question) {
     return questionService.saveQuestion(question);
 }
}
