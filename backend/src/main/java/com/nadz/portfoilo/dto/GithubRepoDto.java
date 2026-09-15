package com.nadz.portfolio.dto;

public class GitHubRepoDto {
    private String name, description, url, homepage, language, updatedAt;
    private int stars, forks;

    public GitHubRepoDto(String name, String description, String url, String homepage,
                         String language, int stars, int forks, String updatedAt) {
        this.name = name; this.description = description; this.url = url;
        this.homepage = homepage; this.language = language;
        this.stars = stars; this.forks = forks; this.updatedAt = updatedAt;
    }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getUrl() { return url; }
    public String getHomepage() { return homepage; }
    public String getLanguage() { return language; }
    public int getStars() { return stars; }
    public int getForks() { return forks; }
    public String getUpdatedAt() { return updatedAt; }
}
