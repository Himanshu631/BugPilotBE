package com.techio.bugpilot.project.repository;

import com.techio.bugpilot.project.entity.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {
    List<Project> findByClientId(String clientId);
}

