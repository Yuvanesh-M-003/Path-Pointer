package com.pathpointer.backend.dto;

public class ProblemRecommendationDto {

    private Long recommendationItemId;
    private String title;
    private String difficulty;
    private String topic;
    private String url;
    private Boolean solved;

    public ProblemRecommendationDto() {}

    public ProblemRecommendationDto(Long recommendationItemId, String title, String difficulty,
                                    String topic, String url, Boolean solved) {
        this.recommendationItemId = recommendationItemId;
        this.title = title;
        this.difficulty = difficulty;
        this.topic = topic;
        this.url = url;
        this.solved = solved;
    }

    public Long getRecommendationItemId() {
        return recommendationItemId;
    }

    public void setRecommendationItemId(Long recommendationItemId) {
        this.recommendationItemId = recommendationItemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getSolved() {
        return solved;
    }

    public void setSolved(Boolean solved) {
        this.solved = solved;
    }
}