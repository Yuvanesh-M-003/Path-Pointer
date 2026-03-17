package com.pathpointer.backend.service;

import com.pathpointer.backend.dto.ProblemRecommendationDto;
import com.pathpointer.backend.dto.SolveRecommendationResponse;
import com.pathpointer.backend.entity.*;
import com.pathpointer.backend.model.User;
import com.pathpointer.backend.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class RecommendationService {

    private final UserRepository userRepository;
    private final ProblemCatalogRepository problemCatalogRepository;
    private final DailyRecommendationRepository dailyRecommendationRepository;
    private final RecommendationItemRepository recommendationItemRepository;

    public RecommendationService(UserRepository userRepository,
                                 ProblemCatalogRepository problemCatalogRepository,
                                 DailyRecommendationRepository dailyRecommendationRepository,
                                 RecommendationItemRepository recommendationItemRepository) {
        this.userRepository = userRepository;
        this.problemCatalogRepository = problemCatalogRepository;
        this.dailyRecommendationRepository = dailyRecommendationRepository;
        this.recommendationItemRepository = recommendationItemRepository;
    }

    @Transactional
    public SolveRecommendationResponse getTodayRecommendations(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocalDate today = LocalDate.now();

        Optional<DailyRecommendation> existing =
                dailyRecommendationRepository.findByUserAndRecommendationDate(user, today);

        String stage = determineStage();

        if (existing.isPresent()) {
            return buildResponse(existing.get(), stage);
        }

        DailyRecommendation recommendation = generateRecommendations(user, stage);

        return buildResponse(recommendation, stage);
    }

    private DailyRecommendation generateRecommendations(User user, String stage) {

        List<String> topics = getTopicsFromUser(user);

        List<ProblemCatalog> selectedProblems = new ArrayList<>();

        for (String topic : topics) {

            List<ProblemCatalog> topicProblems =
                    problemCatalogRepository.findByTopicContainingIgnoreCase(topic);

            for (ProblemCatalog problem : topicProblems) {

                if (selectedProblems.size() >= 7)
                    break;

                if (!containsSlug(selectedProblems, problem.getTitleSlug())) {
                    selectedProblems.add(problem);
                }
            }

            if (selectedProblems.size() >= 7)
                break;
        }

        DailyRecommendation daily = new DailyRecommendation();
        daily.setUser(user);
        daily.setRecommendationDate(LocalDate.now());
        daily.setStatus(RecommendationStatus.PENDING);

        daily = dailyRecommendationRepository.save(daily);

        int order = 1;

        for (ProblemCatalog problem : selectedProblems) {

            RecommendationItem item = new RecommendationItem();
            item.setDailyRecommendation(daily);
            item.setProblemCatalog(problem);
            item.setRecommendedOrder(order++);

            recommendationItemRepository.save(item);
        }

        return daily;
    }

    private String determineStage() {
        return "INTERMEDIATE";
    }

    private List<String> getTopicsFromUser(User user) {

        String topics = user.getTopics();

        if (topics == null || topics.isBlank()) {
            return List.of("Array", "String");
        }

        return Arrays.stream(topics.split(","))
                .map(String::trim)
                .filter(t -> !t.isBlank())
                .toList();
    }

    private boolean containsSlug(List<ProblemCatalog> problems, String slug) {

        return problems.stream()
                .anyMatch(p -> p.getTitleSlug().equalsIgnoreCase(slug));
    }

    private SolveRecommendationResponse buildResponse(DailyRecommendation daily, String stage) {

        List<RecommendationItem> items =
                recommendationItemRepository
                        .findByDailyRecommendationOrderByRecommendedOrderAsc(daily);

        List<ProblemRecommendationDto> responses = items.stream()
                .map(item -> new ProblemRecommendationDto(
                        item.getId(),
                        item.getProblemCatalog().getTitle(),
                        item.getProblemCatalog().getDifficulty().name(),
                        item.getProblemCatalog().getTopic(),
                        item.getProblemCatalog().getUrl(),
                        item.getSolved()
                ))
                .toList();

        return new SolveRecommendationResponse(
                daily.getRecommendationDate(),
                stage,
                responses.size(),
                responses
        );
    }
}