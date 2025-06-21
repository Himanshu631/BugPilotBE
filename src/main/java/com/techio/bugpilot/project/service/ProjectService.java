package com.techio.bugpilot.project.service;

import com.techio.bugpilot.project.entity.Project;
import com.techio.bugpilot.project.payload.CreateProjectRequest;
import com.techio.bugpilot.project.payload.ProjectResponse;
import com.techio.bugpilot.project.repository.ProjectRepository;
import com.techio.bugpilot.utility.GeneralUtility;
import com.techio.bugpilot.utility.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public GenericResponse<ProjectResponse> createProject(CreateProjectRequest request, String clientId, String createdBy) {
        Project project = Project.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .clientId(clientId)
                .projectManagerId(request.getProjectManagerId())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .tags(request.getTags())
                .workflowId(request.getWorkflowId())
                .customFields(request.getCustomFields())
                .slaConfigId(request.getSlaConfigId())
                .createdBy(createdBy)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status("ACTIVE")
                .deleted(false)
                .build();

        Project saved = projectRepository.save(project);
        return GeneralUtility.success("Project Created Successfully", mapToResponse(saved));
    }

    public GenericResponse<List<ProjectResponse>> getProjectsByClient(String clientId) {
        List<Project> projects = projectRepository.findByClientId(clientId);
        List<ProjectResponse> response = projects.stream()
                .map(this::mapToResponse)
                .toList();

        return GeneralUtility.success("Projects fetched", response);
    }

    public GenericResponse<ProjectResponse> updateProject(String id, CreateProjectRequest request) {
        Project existing = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        existing.setProjectManagerId(request.getProjectManagerId());
        existing.setStartDate(request.getStartDate());
        existing.setEndDate(request.getEndDate());
        existing.setTags(request.getTags());
        existing.setWorkflowId(request.getWorkflowId());
        existing.setCustomFields(request.getCustomFields());
        existing.setSlaConfigId(request.getSlaConfigId());
        existing.setUpdatedAt(LocalDateTime.now());

        Project updated = projectRepository.save(existing);
        return GeneralUtility.success("Project Updated", mapToResponse(updated));
    }

    public GenericResponse<String> deleteProject(String id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        project.setDeleted(true);
        project.setUpdatedAt(LocalDateTime.now());
        projectRepository.save(project);
        return GeneralUtility.success("Project soft-deleted", id);
    }

    private ProjectResponse mapToResponse(Project project) {
        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .clientId(project.getClientId())
                .projectManagerId(project.getProjectManagerId())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .tags(project.getTags())
                .workflowId(project.getWorkflowId())
                .customFields(project.getCustomFields())
                .slaConfigId(project.getSlaConfigId())
                .createdBy(project.getCreatedBy())
                .createdAt(project.getCreatedAt())
                .updatedAt(project.getUpdatedAt())
                .updatedBy(project.getUpdatedBy())
                .status(project.getStatus())
                .build();
    }
}


