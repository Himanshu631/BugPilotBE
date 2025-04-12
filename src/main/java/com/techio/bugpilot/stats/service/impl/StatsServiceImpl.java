package com.techio.bugpilot.stats.service.impl;

import com.techio.bugpilot.stats.entity.CummulativeStats;
import com.techio.bugpilot.stats.payload.CummulativeStatsDto;
import com.techio.bugpilot.stats.repository.StatsRepository;
import com.techio.bugpilot.stats.service.StatsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsServiceImpl implements StatsService {

    private final StatsRepository statsRepository;

    public StatsServiceImpl(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    @Override
    public ResponseEntity<?> fetchCummulativeStats() {
        List<CummulativeStats> cummulativeStatsOptional = statsRepository.findAll();
        if (cummulativeStatsOptional.isEmpty()) {
            return new ResponseEntity<>(List.of(), HttpStatus.NOT_FOUND);
        } else {
            CummulativeStats cummulativeStats = cummulativeStatsOptional.get(0);
            return new ResponseEntity<>(cummulativeStats, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> createCummulativeStats(final CummulativeStatsDto cummulativeStatsDto) {
        CummulativeStats cummulativeStats = new CummulativeStats();
        cummulativeStats.setClientId(cummulativeStatsDto.getClientId());
        cummulativeStats.setProjectId(cummulativeStatsDto.getProjectId());
        cummulativeStats.setOpenBugs(cummulativeStatsDto.getOpenBugs());
        cummulativeStats.setAvgResolutionTime(cummulativeStatsDto.getAvgResolutionTime());
        cummulativeStats.setTotalBugs(cummulativeStatsDto.getTotalBugs());
        cummulativeStats.setAwaitingReplyBugs(cummulativeStatsDto.getAwaitingReplyBugs());
        cummulativeStats.setInProgressBugs(cummulativeStatsDto.getInProgressBugs());
        cummulativeStats.setResolvedBugs(cummulativeStatsDto.getResolvedBugs());

        statsRepository.save(cummulativeStats);

        return new ResponseEntity<>(cummulativeStats, HttpStatus.CREATED);
    }
}
