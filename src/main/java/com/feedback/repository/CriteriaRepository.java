package com.feedback.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feedback.model.Criteria;

@Repository
public interface CriteriaRepository extends JpaRepository<Criteria, Long> {
	
}
