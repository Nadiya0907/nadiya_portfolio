package com.nadz.portfolio.service;

import com.nadz.portfolio.dto.ProjectDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProjectService {

    public List<ProjectDto> getAll() {
        return List.of(
            new ProjectDto(
                "fraud-detection", "Machine Learning",
                "Credit Card Fraud Detection",
                "A machine-learning project that analyzes credit card transaction data and predicts potentially fraudulent transactions.",
                List.of("Python", "Pandas", "Scikit-learn", "Matplotlib"),
                List.of(Map.of("value", "99.92%", "label", "Accuracy"),
                        Map.of("value", "0.9559", "label", "ROC-AUC")),
                "#"
            ),
            new ProjectDto(
                "spring-boot-api", "Backend",
                "Spring Boot REST API",
                "A backend REST API built with Java and Spring Boot with database integration and CRUD functionality.",
                List.of("Java", "Spring Boot", "Spring Data JPA", "MySQL", "REST API"),
                List.of(), "#"
            ),
            new ProjectDto(
                "python-automation", "Automation",
                "Python Data Automation",
                "CSV/Excel input to Python processing to data cleaning and transformation to final output.",
                List.of("Python", "Pandas", "OpenPyXL", "Automation"),
                List.of(), "#"
            )
        );
    }
}
