package com.feedback.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.feedback.model.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
 List<Question> findByCriteriaId(Long criteriaId);
}
