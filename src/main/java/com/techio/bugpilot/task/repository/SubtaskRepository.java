package com.techio.bugpilot.task.repository;

import com.techio.bugpilot.task.entity.Subtask;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface SubtaskRepository extends MongoRepository<Subtask, String> {
    Page<Subtask> findByTaskId(String taskId, Pageable pageable);
}


