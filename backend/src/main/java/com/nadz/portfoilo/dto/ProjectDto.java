package com.nadz.portfolio.dto;

import java.util.List;
import java.util.Map;

public class ProjectDto {
    private String id, badge, title, description, link;
    private List<String> tech;
    private List<Map<String, String>> metrics;

    public ProjectDto(String id, String badge, String title, String description,
                      List<String> tech, List<Map<String, String>> metrics, String link) {
        this.id = id; this.badge = badge; this.title = title;
        this.description = description; this.tech = tech;
        this.metrics = metrics; this.link = link;
    }
    public String getId() { return id; }
    public String getBadge() { return badge; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public List<String> getTech() { return tech; }
    public List<Map<String, String>> getMetrics() { return metrics; }
    public String getLink() { return link; }
}
