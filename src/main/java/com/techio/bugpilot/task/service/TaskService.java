package com.techio.bugpilot.task.service;

import com.techio.bugpilot.task.entity.Task;
import com.techio.bugpilot.task.payload.CreateTaskRequest;
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

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepo;

    public GenericResponse<Task> createTask(CreateTaskRequest req) {
        Task task = new Task();
        task.setTitle(req.getTitle());
        task.setDescription(req.getDescription());
        task.setProjectId(req.getProjectId());
        task.setModuleId(req.getModuleId());
        task.setAssignedToUserId(req.getAssignedToUserId());
        task.setPriority(req.getPriority());
        task.setStatus(req.getStatus());
        task.setDueDate(req.getDueDate());
        task.setTags(req.getTags());
        task.setCreatedBy(req.getCreatedBy());
        task.setCreatedAt(Instant.now());

        return GeneralUtility.success(taskRepo.save(task));
    }

    public GenericResponse<List<Task>> getTasksByModule(String moduleId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Task> taskPage = taskRepo.findByModuleId(moduleId, pageable);

        GenericResponse<List<Task>> response = new GenericResponse<>();
        response.setSuccess(true);
        response.setMessage("Tasks fetched successfully");
        response.setData(taskPage.getContent());
        response.setTotalRecords((int) taskPage.getTotalElements());

        return response;
    }



    public GenericResponse<Task> getById(String id) {
        return taskRepo.findById(id)
                .map(GeneralUtility::success)
                .orElse(GeneralUtility.failure("Task not found"));
    }

    public GenericResponse<Void> delete(String id) {
        taskRepo.deleteById(id);
        return GeneralUtility.success(null);
    }
}

