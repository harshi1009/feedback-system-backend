package com.feedback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feedback.model.Question;
import com.feedback.repository.QuestionRepository;

import java.util.List;

@Service
public class QuestionService {
 @Autowired
 private QuestionRepository questionRepository;

 public List<Question> getQuestionsByCriteriaId(Long criteriaId) {
     return questionRepository.findByCriteriaId(criteriaId);
 }

 public Question saveQuestion(Question question) {
     return questionRepository.save(question);
 }
}
