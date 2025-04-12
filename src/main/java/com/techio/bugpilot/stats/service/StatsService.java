package com.techio.bugpilot.stats.service;

import com.techio.bugpilot.stats.payload.CummulativeStatsDto;
import org.springframework.http.ResponseEntity;

public interface StatsService {
    ResponseEntity<?> fetchCummulativeStats();
    ResponseEntity<?> createCummulativeStats(CummulativeStatsDto cummulativeStatsDto);
}
