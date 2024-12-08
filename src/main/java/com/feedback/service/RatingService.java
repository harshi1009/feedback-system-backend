package com.feedback.service;

import com.feedback.model.Rating;
import com.feedback.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> saveRatings(List<Rating> ratings) {
        // Save the list of ratings to the database
        return ratingRepository.saveAll(ratings);
    }

    public List<Rating> getRatingsByCriteria(Long criteriaId) {
        // Fetch ratings by criteriaId and include questionText
        return ratingRepository.findByCriteriaId(criteriaId);
    }
}
