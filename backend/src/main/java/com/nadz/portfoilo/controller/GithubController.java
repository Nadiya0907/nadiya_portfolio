package com.nadz.portfolio.controller;

import com.nadz.portfolio.dto.GitHubRepoDto;
import com.nadz.portfolio.service.GitHubService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/github")
public class GitHubController {

    private final GitHubService service;

    public GitHubController(GitHubService service) { this.service = service; }

    @GetMapping("/repos")
    public Map<String, Object> repos(@RequestParam(defaultValue = "6") int limit) {
        List<GitHubRepoDto> repos = service.getTopRepos(Math.min(limit, 30));
        return Map.of("success", true, "repos", repos);
    }
}
