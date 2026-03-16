package com.pathpointer.backend.dto;

import java.time.LocalDate;
import java.util.List;

public class SolveRecommendationResponse {

    private LocalDate date;
    private String stage;
    private int totalRecommended;
    private List<ProblemRecommendationDto> problems;

    public SolveRecommendationResponse() {}

    public SolveRecommendationResponse(LocalDate date, String stage, int totalRecommended,
                                       List<ProblemRecommendationDto> problems) {
        this.date = date;
        this.stage = stage;
        this.totalRecommended = totalRecommended;
        this.problems = problems;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public int getTotalRecommended() {
        return totalRecommended;
    }

    public void setTotalRecommended(int totalRecommended) {
        this.totalRecommended = totalRecommended;
    }

    public List<ProblemRecommendationDto> getProblems() {
        return problems;
    }

    public void setProblems(List<ProblemRecommendationDto> problems) {
        this.problems = problems;
    }
}