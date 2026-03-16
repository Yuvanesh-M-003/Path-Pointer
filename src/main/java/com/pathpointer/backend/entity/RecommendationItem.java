package com.pathpointer.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recommendation_items")
public class RecommendationItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "daily_recommendation_id", nullable = false)
    private DailyRecommendation dailyRecommendation;

    @ManyToOne
    @JoinColumn(name = "problem_catalog_id", nullable = false)
    private ProblemCatalog problemCatalog;

    private Integer recommendedOrder;

    private Boolean clicked = false;
    private LocalDateTime clickedAt;

    private Boolean solved = false;
    private LocalDateTime solvedAt;

    public RecommendationItem() {}

    public Long getId() {
        return id;
    }

    public DailyRecommendation getDailyRecommendation() {
        return dailyRecommendation;
    }

    public ProblemCatalog getProblemCatalog() {
        return problemCatalog;
    }

    public Integer getRecommendedOrder() {
        return recommendedOrder;
    }

    public Boolean getClicked() {
        return clicked;
    }

    public LocalDateTime getClickedAt() {
        return clickedAt;
    }

    public Boolean getSolved() {
        return solved;
    }

    public LocalDateTime getSolvedAt() {
        return solvedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDailyRecommendation(DailyRecommendation dailyRecommendation) {
        this.dailyRecommendation = dailyRecommendation;
    }

    public void setProblemCatalog(ProblemCatalog problemCatalog) {
        this.problemCatalog = problemCatalog;
    }

    public void setRecommendedOrder(Integer recommendedOrder) {
        this.recommendedOrder = recommendedOrder;
    }

    public void setClicked(Boolean clicked) {
        this.clicked = clicked;
    }

    public void setClickedAt(LocalDateTime clickedAt) {
        this.clickedAt = clickedAt;
    }

    public void setSolved(Boolean solved) {
        this.solved = solved;
    }

    public void setSolvedAt(LocalDateTime solvedAt) {
        this.solvedAt = solvedAt;
    }
}