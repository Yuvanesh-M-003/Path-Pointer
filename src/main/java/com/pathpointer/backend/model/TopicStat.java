package com.pathpointer.backend.model;

public class TopicStat {

    private String topic;
    private int solved;

    public TopicStat() {
    }

    public TopicStat(String topic, int solved) {
        this.topic = topic;
        this.solved = solved;
    }

    public String getTopic() {
        return topic;
    }

    public int getSolved() {
        return solved;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setSolved(int solved) {
        this.solved = solved;
    }
}