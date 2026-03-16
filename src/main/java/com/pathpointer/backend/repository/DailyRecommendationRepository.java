package com.pathpointer.backend.repository;

import com.pathpointer.backend.entity.DailyRecommendation;
import com.pathpointer.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface DailyRecommendationRepository extends JpaRepository<DailyRecommendation, Long> {

    Optional<DailyRecommendation> findByUserAndRecommendationDate(User user, LocalDate recommendationDate);
}