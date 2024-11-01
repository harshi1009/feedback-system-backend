package com.feedback.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.feedback.model.Criteria;

public interface CriteriaRepository extends JpaRepository<Criteria, Long> {
}
