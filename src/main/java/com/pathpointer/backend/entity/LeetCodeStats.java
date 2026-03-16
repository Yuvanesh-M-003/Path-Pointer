package com.pathpointer.backend.entity;

import com.pathpointer.backend.model.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "leetcode_stats")
public class LeetCodeStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    private Integer totalSolved = 0;
    private Integer easySolved = 0;
    private Integer mediumSolved = 0;
    private Integer hardSolved = 0;

    private LocalDateTime lastSyncedAt = LocalDateTime.now();

    public LeetCodeStats() {}

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Integer getTotalSolved() {
        return totalSolved;
    }

    public Integer getEasySolved() {
        return easySolved;
    }

    public Integer getMediumSolved() {
        return mediumSolved;
    }

    public Integer getHardSolved() {
        return hardSolved;
    }

    public LocalDateTime getLastSyncedAt() {
        return lastSyncedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTotalSolved(Integer totalSolved) {
        this.totalSolved = totalSolved;
    }

    public void setEasySolved(Integer easySolved) {
        this.easySolved = easySolved;
    }

    public void setMediumSolved(Integer mediumSolved) {
        this.mediumSolved = mediumSolved;
    }

    public void setHardSolved(Integer hardSolved) {
        this.hardSolved = hardSolved;
    }

    public void setLastSyncedAt(LocalDateTime lastSyncedAt) {
        this.lastSyncedAt = lastSyncedAt;
    }
}