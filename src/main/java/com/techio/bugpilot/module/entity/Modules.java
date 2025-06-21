package com.techio.bugpilot.module.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Document(collection = "modules")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Modules {

    @Id
    private String id;

    private String name;
    private String description;

    private String projectId;
    private String ownerId; // Optional - person responsible for this module
    private List<String> tags; // Custom labels (e.g., ["frontend", "critical"])
    private String status; // ACTIVE, ARCHIVED, DRAFT
    private String priority; // LOW, MEDIUM, HIGH
    private Integer order;
    private Integer estimatedEffort;

    private Map<String, Object> customFields;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

