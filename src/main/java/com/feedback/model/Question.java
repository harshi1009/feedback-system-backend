package com.feedback.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "criteria_id", nullable = false)
    @JsonBackReference // Prevents serialization of the Criteria in the Question JSON
    private Criteria criteria;


    @Column(name = "criteria_id", insertable = false, updatable = false) // Maps the column directly but prevents conflicts
    private Long criteriaId;

    private String text;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public Long getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(Long criteriaId) {
        this.criteriaId = criteriaId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
