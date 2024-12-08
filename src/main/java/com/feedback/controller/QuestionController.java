package com.feedback.controller;

import com.feedback.model.Question;
import com.feedback.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")

public class QuestionController {

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionService questionService;

    // Get questions by criteriaId
    @GetMapping("/{criteriaId}")
    public ResponseEntity<List<Question>> getQuestions(@PathVariable Long criteriaId) {
        logger.info("Fetching questions for criteriaId: {}", criteriaId);
        try {
            return ResponseEntity.ok(questionService.getQuestionsByCriteria(criteriaId));
        } catch (Exception e) {
            logger.error("Error fetching questions for criteriaId: {}", criteriaId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // Add a question to a criteria
    @PostMapping("/{criteriaId}")
    public ResponseEntity<Question> addQuestion(@PathVariable Long criteriaId, @RequestBody Question question) {
        logger.info("Adding question for criteriaId: {}", criteriaId);
        try {
            return ResponseEntity.ok(questionService.addQuestion(criteriaId, question));
        } catch (RuntimeException e) {
            logger.error("Error adding question for criteriaId: {}", criteriaId, e);
            return ResponseEntity.badRequest().build();
        }
    }

    // Update a question
    @PutMapping("/{questionId}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long questionId, @RequestBody Question updatedQuestion) {
        logger.info("Updating question with ID: {}", questionId);
        try {
            return ResponseEntity.ok(questionService.updateQuestion(questionId, updatedQuestion));
        } catch (RuntimeException e) {
            logger.error("Error updating question with ID: {}", questionId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // Delete a question
    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {
        try {
            questionService.deleteQuestion(questionId);
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            logger.error("Cannot delete question with ID {} as it is referenced elsewhere", questionId, e);
            return ResponseEntity.status(HttpStatus.CONFLICT).build();  // 409 Conflict
        } catch (RuntimeException e) {
            logger.error("Error deleting question with ID: {}", questionId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

}
