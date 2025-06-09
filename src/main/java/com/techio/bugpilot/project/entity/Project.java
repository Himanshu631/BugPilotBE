package com.techio.bugpilot.project.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "projects")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private String description;
    private String clientId;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

