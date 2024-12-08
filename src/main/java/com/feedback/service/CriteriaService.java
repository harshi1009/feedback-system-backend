package com.feedback.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feedback.model.Criteria;
import com.feedback.repository.CriteriaRepository;

import java.util.List;

@Service
public class CriteriaService {

    @Autowired
    private CriteriaRepository criteriaRepository;

    public List<Criteria> getAllCriteria() {
        return criteriaRepository.findAll();
    }

    public Criteria addCriteria(Criteria criteria) {
        // Set position as the last one in the list
        int position = (int) criteriaRepository.count();
        criteria.setPosition(position);
        return criteriaRepository.save(criteria);
    }

    public Criteria updateCriteria(Long id, Criteria updatedCriteria) {
        return criteriaRepository.findById(id).map(existing -> {
            existing.setText(updatedCriteria.getText());
            return criteriaRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Criteria not found"));
    }

    public void deleteCriteria(Long id) {
        if (!criteriaRepository.existsById(id)) {
            throw new RuntimeException("Criteria not found");
        }
        criteriaRepository.deleteById(id);
    }

    public void reorderCriteria(List<Criteria> reorderedList) {
        for (int i = 0; i < reorderedList.size(); i++) {
            Criteria criteria = reorderedList.get(i);
            criteria.setPosition(i);
            criteriaRepository.save(criteria);
        }
    }
}

