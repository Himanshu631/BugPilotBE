package com.techio.bugpilot.task.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document(collection = "subtasks")
@Data
public class Subtask {
    @Id
    private String id = UUID.randomUUID().toString();
    private String title;
    private String description;
    private String taskId;
    private String assignedToUserId;
    private String status;
    private String createdBy;
    private Instant createdAt = Instant.now();
}

