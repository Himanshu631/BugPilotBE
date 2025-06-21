package com.techio.bugpilot.project.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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
    private String status;
    private List<String> members;
    private String projectManagerId;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> tags;
    private String workflowId;
    private Map<String, Object> customFields;
    private String slaConfigId;
    private String visibility;
    private boolean deleted;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
