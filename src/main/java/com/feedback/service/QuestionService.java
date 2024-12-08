package com.feedback.service;

import com.feedback.model.Criteria;
import com.feedback.model.Question;
import com.feedback.repository.CriteriaRepository;
import com.feedback.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CriteriaRepository criteriaRepository;  // Add this to access Criteria data

    // Get questions by criteriaId
    public List<Question> getQuestionsByCriteria(Long criteriaId) {
        return questionRepository.findByCriteriaId(criteriaId);
    }

    public Question addQuestion(Long criteriaId, Question question) {
        Criteria criteria = criteriaRepository.findById(criteriaId)
                .orElseThrow(() -> new RuntimeException("Criteria not found: " + criteriaId));

        question.setCriteria(criteria); // Associate the question with the criteria
        return questionRepository.save(question);
    }


    // Update an existing question
    public Question updateQuestion(Long questionId, Question updatedQuestion) {
        // Fetch the existing Question by ID
        Question existingQuestion = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found: " + questionId));

        // Update the Text of the Question
        existingQuestion.setText(updatedQuestion.getText());

        // If the Criteria is updated, fetch the new Criteria and set it
        if (updatedQuestion.getCriteria() != null) {
            Criteria criteria = updatedQuestion.getCriteria(); // Get new Criteria
            existingQuestion.setCriteria(criteria);
        }

        // Save and return the updated Question
        return questionRepository.save(existingQuestion);
    }

    // Delete a question
    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }
}
