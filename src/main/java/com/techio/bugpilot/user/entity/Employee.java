package com.techio.bugpilot.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String id;
    private String name;
    private String email;
    private String clientId;
    private String departmentId;
}

