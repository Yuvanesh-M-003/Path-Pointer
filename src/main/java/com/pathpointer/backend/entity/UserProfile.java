package com.pathpointer.backend.entity;

import com.pathpointer.backend.model.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private String leetcodeUsername;

    private String preferredLanguage;

    @Column(columnDefinition = "TEXT")
    private String topicsInterested;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public UserProfile() {}

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getLeetcodeUsername() {
        return leetcodeUsername;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public String getTopicsInterested() {
        return topicsInterested;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLeetcodeUsername(String leetcodeUsername) {
        this.leetcodeUsername = leetcodeUsername;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public void setTopicsInterested(String topicsInterested) {
        this.topicsInterested = topicsInterested;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}