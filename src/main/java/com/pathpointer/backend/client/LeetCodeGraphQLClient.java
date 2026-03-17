package com.pathpointer.backend.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Component
public class LeetCodeGraphQLClient {

    private final String URL = "https://leetcode.com/graphql";

    public String fetchUserSolved(String sessionToken) {

        RestTemplate restTemplate = new RestTemplate();

        String query = "{ \"query\": \"{ problemsetQuestionList: questionList(categorySlug: \\\"\\\", limit: 50) { total: totalNum } }\" }";

        HttpHeaders headers = new HttpHeaders();

        headers.add("Cookie", "LEETCODE_SESSION=" + sessionToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(query, headers);

        ResponseEntity<String> response =
                restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }
}