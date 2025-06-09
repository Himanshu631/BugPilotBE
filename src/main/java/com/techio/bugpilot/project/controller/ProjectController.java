package com.techio.bugpilot.project.controller;

import com.techio.bugpilot.project.payload.CreateProjectRequest;
import com.techio.bugpilot.project.payload.ProjectResponse;
import com.techio.bugpilot.project.service.ProjectService;
import com.techio.bugpilot.utility.AuthContextUtil;
import com.techio.bugpilot.utility.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final AuthContextUtil authContextUtil;

    @PostMapping
    public ResponseEntity<GenericResponse<ProjectResponse>> createProject(@RequestBody CreateProjectRequest request) {
        return ResponseEntity.ok(projectService.createProject(request, authContextUtil.getClientIdOrThrow(), authContextUtil.getUserId()));
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<ProjectResponse>>> getProjects() {
        return ResponseEntity.ok(projectService.getProjectsByClient(authContextUtil.getClientIdOrThrow()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<ProjectResponse>> updateProject(@PathVariable String id,
                                                                          @RequestBody CreateProjectRequest request) {
        return ResponseEntity.ok(projectService.updateProject(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<String>> deleteProject(@PathVariable String id) {
        return ResponseEntity.ok(projectService.deleteProject(id));
    }
}

