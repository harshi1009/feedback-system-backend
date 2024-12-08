package com.feedback.repository;

import com.feedback.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByCriteriaId(Long criteriaId);
}
