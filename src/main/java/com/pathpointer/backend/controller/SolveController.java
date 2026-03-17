package com.pathpointer.backend.controller;

import com.pathpointer.backend.dto.SolveRecommendationResponse;
import com.pathpointer.backend.service.RecommendationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/solve")
public class SolveController {

    private final RecommendationService recommendationService;

    public SolveController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/recommendations")
    public SolveRecommendationResponse getRecommendations(@RequestParam String email) {
        return recommendationService.getTodayRecommendations(email);
    }
}