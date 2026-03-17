package com.pathpointer.backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pathpointer.backend.model.TopicStat;
import com.pathpointer.backend.model.User;
import com.pathpointer.backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProgressService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();
    private final UserRepository userRepository;

    // cache by email
    private final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();

    // avoid hitting external API too often
    private static final Duration CACHE_TTL = Duration.ofMinutes(15);

    public ProgressService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<TopicStat> getTopicProgressByEmail(String email) {

        CacheEntry cached = cache.get(email);

        // 1) If cache is still fresh, return it immediately
        if (cached != null && !isExpired(cached.timestamp())) {
            System.out.println("Returning fresh cached progress for email: " + email);
            return cached.data();
        }

        try {
            User user = userRepository.findByEmail(email).orElse(null);

            if (user == null) {
                System.out.println("No user found for email: " + email);
                return getCachedOrEmpty(email);
            }

            String username = user.getLeetcodeId();

            if (username == null || username.isBlank()) {
                System.out.println("No LeetCode username stored for email: " + email);
                return getCachedOrEmpty(email);
            }

            String url = "https://alfa-leetcode-api.onrender.com/" + username + "/skill";

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            String body = response.getBody();

            System.out.println("FETCHING FOR EMAIL = " + email);
            System.out.println("FETCHING FOR LEETCODE USERNAME = " + username);
            System.out.println("RAW API RESPONSE:");
            System.out.println(body);

            List<TopicStat> result = parseTopicStats(body);

            // 2) Save successful result to cache
            if (!result.isEmpty()) {
                cache.put(email, new CacheEntry(result, Instant.now()));
                System.out.println("Cached " + result.size() + " topics for email: " + email);
            }

            return result;

        } catch (HttpClientErrorException.TooManyRequests e) {
            System.out.println("429 rate limit hit for email: " + email);
            System.out.println("Returning cached data instead of failing.");
            return getCachedOrEmpty(email);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("External API failed. Returning cached data if available.");
            return getCachedOrEmpty(email);
        }
    }

    private List<TopicStat> parseTopicStats(String body) {

        List<TopicStat> result = new ArrayList<>();

        try {
            if (body == null || body.isBlank()) {
                return result;
            }

            JsonNode root = mapper.readTree(body);

            // Expected format:
            // {
            //   "fundamental": [ ... ],
            //   "intermediate": [ ... ],
            //   "advanced": [ ... ]
            // }

            Iterator<String> fields = root.fieldNames();

            while (fields.hasNext()) {
                String level = fields.next();
                JsonNode topics = root.get(level);

                if (topics != null && topics.isArray()) {
                    for (JsonNode topic : topics) {
                        String name = topic.has("tagName")
                                ? topic.get("tagName").asText()
                                : "Unknown";

                        int solved = topic.has("problemsSolved")
                                ? topic.get("problemsSolved").asInt()
                                : 0;

                        result.add(new TopicStat(name, solved));
                    }
                }
            }

            System.out.println("PARSED TOPICS COUNT = " + result.size());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private List<TopicStat> getCachedOrEmpty(String email) {
        CacheEntry cached = cache.get(email);

        if (cached != null && cached.data() != null && !cached.data().isEmpty()) {
            System.out.println("Returning OLD cached data for email: " + email);
            return cached.data();
        }

        System.out.println("No cached data found for email: " + email);
        return new ArrayList<>();
    }

    private boolean isExpired(Instant timestamp) {
        return Instant.now().isAfter(timestamp.plus(CACHE_TTL));
    }

    private record CacheEntry(List<TopicStat> data, Instant timestamp) {}
}