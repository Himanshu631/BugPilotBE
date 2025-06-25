package com.techio.bugpilot.task.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Document(collection = "tasks")
@Data
public class Task {
    @Id
    private String id = UUID.randomUUID().toString();
    private String title;
    private String description;
    private String projectId;
    private String moduleId;
    private String assignedToUserId;
    private String priority;
    private String status;
    private Instant dueDate;
    private List<String> tags;
    private String createdBy;
    private Instant createdAt = Instant.now();
}

