package com.techio.bugpilot.task.payload;

import lombok.Data;

@Data
public class CreateSubtaskRequest {
    private String title;
    private String description;
    private String taskId;
    private String assignedToUserId;
    private String status;
    private String createdBy;
}

