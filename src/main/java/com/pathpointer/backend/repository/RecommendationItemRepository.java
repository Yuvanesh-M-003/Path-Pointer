package com.pathpointer.backend.repository;

import com.pathpointer.backend.entity.DailyRecommendation;
import com.pathpointer.backend.entity.RecommendationItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationItemRepository extends JpaRepository<RecommendationItem, Long> {

    List<RecommendationItem> findByDailyRecommendationOrderByRecommendedOrderAsc(DailyRecommendation dailyRecommendation);
}