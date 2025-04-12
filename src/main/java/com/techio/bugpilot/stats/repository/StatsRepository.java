package com.techio.bugpilot.stats.repository;

import com.techio.bugpilot.stats.entity.CummulativeStats;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsRepository extends MongoRepository<CummulativeStats, String> {
}
