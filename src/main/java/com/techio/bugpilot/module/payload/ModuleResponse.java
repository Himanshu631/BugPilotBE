package com.techio.bugpilot.module.payload;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class ModuleResponse {
    private String id;
    private String name;
    private String description;

    private String projectId;
    private String ownerId;
    private List<String> tags;
    private String status;
    private String priority;
    private Integer order;
    private Integer estimatedEffort;

    private Map<String, Object> customFields;

    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

