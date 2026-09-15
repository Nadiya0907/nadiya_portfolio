package com.nadz.portfolio.controller;

import com.nadz.portfolio.dto.ProjectDto;
import com.nadz.portfolio.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) { this.service = service; }

    @GetMapping
    public Map<String, Object> getAll() {
        List<ProjectDto> projects = service.getAll();
        return Map.of("success", true, "projects", projects);
    }
}
