package com.techio.bugpilot.stats.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("cummulative_stats")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CummulativeStats {
    private String id = UUID.randomUUID().toString();
    private String clientId;
    private String projectId;
    private String avgResolutionTime;
    private int totalBugs;
    private int resolvedBugs;
    private int openBugs;
    private int awaitingReplyBugs;
    private int inProgressBugs;
}
