package com.nadz.portfolio.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nadz.portfolio.dto.GitHubRepoDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GitHubService {

    @Value("${github.username:}")
    private String username;

    @Value("${github.token:}")
    private String token;

    private final RestTemplate rest = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    public List<GitHubRepoDto> getTopRepos(int limit) {
        if (username == null || username.isBlank()) return List.of();
        try {
            String url = "https://api.github.com/users/" + username + "/repos?per_page=100&sort=updated";
            HttpHeaders h = new HttpHeaders();
            h.set("Accept", "application/vnd.github+json");
            h.set("User-Agent", "Nadz-Portfolio");
            if (token != null && !token.isBlank()) h.setBearerAuth(token);

            ResponseEntity<String> res = rest.exchange(url, HttpMethod.GET, new HttpEntity<>(h), String.class);
            JsonNode root = mapper.readTree(res.getBody());

            List<GitHubRepoDto> repos = new ArrayList<>();
            for (JsonNode r : root) {
                if (r.path("fork").asBoolean() || r.path("archived").asBoolean()) continue;
                repos.add(new GitHubRepoDto(
                        r.path("name").asText(),
                        r.path("description").isNull() ? "" : r.path("description").asText(),
                        r.path("html_url").asText(),
                        r.path("homepage").isNull() ? "" : r.path("homepage").asText(),
                        r.path("language").isNull() ? "Other" : r.path("language").asText(),
                        r.path("stargazers_count").asInt(),
                        r.path("forks_count").asInt(),
                        r.path("updated_at").asText()
                ));
            }
            repos.sort((a, b) -> Integer.compare(b.getStars(), a.getStars()));
            return repos.size() > limit ? repos.subList(0, limit) : repos;
        } catch (Exception e) {
            System.err.println("GitHub fetch failed: " + e.getMessage());
            return List.of();
        }
    }
}
