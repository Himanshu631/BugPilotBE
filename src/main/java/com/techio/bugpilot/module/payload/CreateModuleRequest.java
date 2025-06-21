package com.techio.bugpilot.module.payload;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CreateModuleRequest {
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
}

