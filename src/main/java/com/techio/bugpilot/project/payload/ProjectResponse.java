package com.techio.bugpilot.project.payload;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class ProjectResponse {
    private String id;
    private String name;
    private String description;
    private String clientId;
    private String projectManagerId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<String> tags;
    private String workflowId;
    private Map<String, Object> customFields;
    private String slaConfigId;
    private String status;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


