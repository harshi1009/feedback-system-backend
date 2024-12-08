package com.feedback.controller;

import com.feedback.model.Rating;
import com.feedback.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@CrossOrigin(origins = "http://localhost:3000")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<List<Rating>> saveRatings(@RequestBody List<Rating> ratings) {
        List<Rating> savedRatings = ratingService.saveRatings(ratings);
        return ResponseEntity.ok(savedRatings);
    }

    @GetMapping("/{criteriaId}")
    public ResponseEntity<List<Rating>> getRatingsByCriteria(@PathVariable Long criteriaId) {
        List<Rating> ratings = ratingService.getRatingsByCriteria(criteriaId);
        return ResponseEntity.ok(ratings);
    }
}
