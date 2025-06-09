package com.techio.bugpilot.project.payload;

import lombok.Data;

@Data
public class CreateProjectRequest {
    private String name;
    private String description;
}

