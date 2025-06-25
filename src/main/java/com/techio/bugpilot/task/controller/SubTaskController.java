package com.techio.bugpilot.task.controller;

import com.techio.bugpilot.task.entity.Subtask;
import com.techio.bugpilot.task.payload.CreateSubtaskRequest;
import com.techio.bugpilot.task.service.SubtaskService;
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
@RequestMapping("/api/v1/subtasks")
@RequiredArgsConstructor
public class SubTaskController {

    private final SubtaskService subtaskService;

    @PostMapping("/task/{taskId}")
    public ResponseEntity<GenericResponse<Subtask>> create(
            @PathVariable String taskId,
            @RequestBody CreateSubtaskRequest req) {
        return ResponseEntity.ok(subtaskService.createSubtask(taskId, req));
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<GenericResponse<List<Subtask>>> byTask(
            @PathVariable String taskId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(subtaskService.getByTaskId(taskId, page, size));
    }


    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<Subtask>> get(@PathVariable String id) {
        return ResponseEntity.ok(subtaskService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Void>> delete(@PathVariable String id) {
        return ResponseEntity.ok(subtaskService.delete(id));
    }
}

