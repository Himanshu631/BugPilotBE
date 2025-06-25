package com.techio.bugpilot.task.payload;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class CreateTaskRequest {
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
}

