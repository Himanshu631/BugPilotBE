package com.techio.bugpilot.task.service;

import com.techio.bugpilot.task.entity.Subtask;
import com.techio.bugpilot.task.entity.Task;
import com.techio.bugpilot.task.payload.CreateSubtaskRequest;
import com.techio.bugpilot.task.repository.SubtaskRepository;
import com.techio.bugpilot.task.repository.TaskRepository;
import com.techio.bugpilot.utility.GeneralUtility;
import com.techio.bugpilot.utility.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubtaskService {
    private final SubtaskRepository subtaskRepo;
    private final TaskRepository taskRepo;

    public GenericResponse<Subtask> createSubtask(String taskId, CreateSubtaskRequest req) {
        Optional<Task> parentTask = taskRepo.findById(taskId);
        if (parentTask.isEmpty()) {
            return GeneralUtility.failure("Invalid taskId: Task does not exist");
        }

        Subtask subtask = new Subtask();
        subtask.setTitle(req.getTitle());
        subtask.setDescription(req.getDescription());
        subtask.setTaskId(taskId);
        subtask.setAssignedToUserId(req.getAssignedToUserId());
        subtask.setStatus(req.getStatus());
        subtask.setCreatedBy(req.getCreatedBy());
        subtask.setCreatedAt(Instant.now());

        return GeneralUtility.success(subtaskRepo.save(subtask));
    }

    public GenericResponse<List<Subtask>> getByTaskId(String taskId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Subtask> subtasksPage = subtaskRepo.findByTaskId(taskId, pageable);

        GenericResponse<List<Subtask>> response = new GenericResponse<>();
        response.setSuccess(true);
        response.setMessage("Subtasks fetched successfully");
        response.setData(subtasksPage.getContent());
        response.setTotalRecords((int) subtasksPage.getTotalElements());

        return response;
    }



    public GenericResponse<Subtask> getById(String id) {
        return subtaskRepo.findById(id)
                .map(GeneralUtility::success)
                .orElse(GeneralUtility.failure("Subtask not found"));
    }

    public GenericResponse<Void> delete(String id) {
        subtaskRepo.deleteById(id);
        return GeneralUtility.success(null);
    }
}

