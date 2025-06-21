package com.techio.bugpilot.project.payload;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class CreateProjectRequest {
    private String name;
    private String description;
    private String clientId;
    private String projectManagerId;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> tags;
    private String workflowId;
    private Map<String, Object> customFields;
    private String slaConfigId;
}

