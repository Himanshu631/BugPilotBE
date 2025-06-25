package com.techio.bugpilot.task.controller;

import com.techio.bugpilot.task.entity.Task;
import com.techio.bugpilot.task.payload.CreateTaskRequest;
import com.techio.bugpilot.task.service.TaskService;
import com.techio.bugpilot.utility.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<GenericResponse<Task>> create(@RequestBody CreateTaskRequest req) {
        return ResponseEntity.ok(taskService.createTask(req));
    }

    @GetMapping("/module/{moduleId}")
    public ResponseEntity<GenericResponse<List<Task>>> byModule(
            @PathVariable String moduleId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(taskService.getTasksByModule(moduleId, page, size));
    }


    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<Task>> get(@PathVariable String id) {
        return ResponseEntity.ok(taskService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Void>> delete(@PathVariable String id) {
        return ResponseEntity.ok(taskService.delete(id));
    }
}

