package com.techio.bugpilot.stats.controller;

import com.techio.bugpilot.stats.payload.CummulativeStatsDto;
import com.techio.bugpilot.stats.service.StatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stats")
@CrossOrigin
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/cummulative")
    public ResponseEntity<?> getCummulativeStats(){
        ResponseEntity<?> responseEntity = statsService.fetchCummulativeStats();
        return new ResponseEntity<>(responseEntity.getBody(), responseEntity.getStatusCode());
    }

    @PostMapping("/cummulative")
    public ResponseEntity<?> createCummulativeStats(final @RequestBody CummulativeStatsDto cummulativeStatsDto){
        ResponseEntity<?> responseEntity = statsService.createCummulativeStats(cummulativeStatsDto);
        return new ResponseEntity<>(responseEntity.getBody(), responseEntity.getStatusCode());
    }
}
