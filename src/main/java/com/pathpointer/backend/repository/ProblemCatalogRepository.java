package com.pathpointer.backend.repository;

import com.pathpointer.backend.entity.ProblemCatalog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemCatalogRepository extends JpaRepository<ProblemCatalog, Long> {

    List<ProblemCatalog> findByTopicContainingIgnoreCase(String topic);
}