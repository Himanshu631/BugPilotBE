package com.techio.bugpilot.module.repository;

import com.techio.bugpilot.module.entity.Modules;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ModuleRepository extends MongoRepository<Modules, String> {
    List<Modules> findByProjectId(String projectId);
}
