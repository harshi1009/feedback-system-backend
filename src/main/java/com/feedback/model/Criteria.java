package com.feedback.model;

//Criteria.java



import java.util.List;

import jakarta.persistence.*;

@Entity
public class Criteria {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String criteriaName;

 @OneToMany(mappedBy = "criteria", cascade = CascadeType.ALL)
 private List<Question> questions;

 // Getters and Setters
 public Long getId() {
     return id;
 }

 public void setId(Long id) {
     this.id = id;
 }

 public String getCriteriaName() {
     return criteriaName;
 }

 public void setCriteriaName(String criteriaName) {
     this.criteriaName = criteriaName;
 }

 public List<Question> getQuestions() {
     return questions;
 }

 public void setQuestions(List<Question> questions) {
     this.questions = questions;
 }
}
