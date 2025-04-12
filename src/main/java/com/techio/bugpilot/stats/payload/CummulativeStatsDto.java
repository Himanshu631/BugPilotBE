package com.techio.bugpilot.stats.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CummulativeStatsDto {
    private String clientId;
    private String projectId;
    private String avgResolutionTime;
    private int totalBugs;
    private int resolvedBugs;
    private int openBugs;
    private int awaitingReplyBugs;
    private int inProgressBugs;
}
