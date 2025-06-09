package com.techio.bugpilot.project.payload;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProjectResponse {
    private String id;
    private String name;
    private String description;
    private String clientId;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

