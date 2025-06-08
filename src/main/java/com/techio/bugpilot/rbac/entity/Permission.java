package com.techio.bugpilot.rbac.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "permissions")
public class Permission {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private String clientId;
    private String description;
}

