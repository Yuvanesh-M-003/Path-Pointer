package com.pathpointer.backend.repository;

import com.pathpointer.backend.entity.LeetCodeStats;
import com.pathpointer.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeetCodeStatsRepository extends JpaRepository<LeetCodeStats, Long> {
    Optional<LeetCodeStats> findByUser(User user);
}