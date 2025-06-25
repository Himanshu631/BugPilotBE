package com.techio.bugpilot.task.repository;

import com.techio.bugpilot.task.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
    Page<Task> findByProjectId(String projectId, Pageable pageable);
    Page<Task> findByModuleId(String moduleId, Pageable pageable);
}

