package com.techio.bugpilot.module.controller;

import com.techio.bugpilot.module.payload.CreateModuleRequest;
import com.techio.bugpilot.module.payload.ModuleResponse;
import com.techio.bugpilot.module.service.ModuleService;
import com.techio.bugpilot.utility.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/modules")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;

    @PostMapping
    public ResponseEntity<GenericResponse<ModuleResponse>> createModule(@RequestBody CreateModuleRequest request) {
        return ResponseEntity.ok(moduleService.createModule(request));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<GenericResponse<List<ModuleResponse>>> getModulesByProject(@PathVariable String projectId) {
        return ResponseEntity.ok(moduleService.getModulesByProject(projectId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<ModuleResponse>> updateModule(
            @PathVariable String id,
            @RequestBody CreateModuleRequest request) {
        return ResponseEntity.ok(moduleService.updateModule(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<String>> deleteModule(@PathVariable String id) {
        return ResponseEntity.ok(moduleService.deleteModule(id));
    }
}

