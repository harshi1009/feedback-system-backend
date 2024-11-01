package com.feedback.model;

import jakarta.persistence.*;

//Question.java


@Entity
public class Question {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String questionText;

 @ManyToOne
 @JoinColumn(name = "criteria_id")
 private Criteria criteria;

 // Getters and Setters
 public Long getId() {
     return id;
 }

 public void setId(Long id) {
     this.id = id;
 }

 public String getQuestionText() {
     return questionText;
 }

 public void setQuestionText(String questionText) {
     this.questionText = questionText;
 }

 public Criteria getCriteria() {
     return criteria;
 }

 public void setCriteria(Criteria criteria) {
     this.criteria = criteria;
 }
}
