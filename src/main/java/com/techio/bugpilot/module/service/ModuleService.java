package com.techio.bugpilot.module.service;

import com.techio.bugpilot.module.payload.CreateModuleRequest;
import com.techio.bugpilot.module.payload.ModuleResponse;
import com.techio.bugpilot.module.repository.ModuleRepository;
import com.techio.bugpilot.utility.AuthContextUtil;
import com.techio.bugpilot.utility.GeneralUtility;
import com.techio.bugpilot.utility.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.techio.bugpilot.module.entity.Modules;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModuleService {

    private final ModuleRepository moduleRepository;
    private final AuthContextUtil authContextUtil;

    public GenericResponse<ModuleResponse> createModule(CreateModuleRequest request) {
        Modules module = Modules.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .projectId(request.getProjectId())
                .ownerId(request.getOwnerId())
                .tags(request.getTags())
                .status(request.getStatus())
                .priority(request.getPriority())
                .order(request.getOrder())
                .estimatedEffort(request.getEstimatedEffort())
                .customFields(request.getCustomFields())
                .createdBy(authContextUtil.getUserId())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Modules saved = moduleRepository.save(module);

        return GeneralUtility.success("Module Created Successfully", toResponse(saved));
    }

    public GenericResponse<List<ModuleResponse>> getModulesByProject(String projectId) {
        List<Modules> modules = moduleRepository.findByProjectId(projectId);
        List<ModuleResponse> response = modules.stream().map(this::toResponse).toList();

        return GeneralUtility.success("Modules fetched successfully", response);
    }

    public GenericResponse<ModuleResponse> updateModule(String id, CreateModuleRequest request) {
        Modules module = moduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Module not found"));

        module.setName(request.getName());
        module.setDescription(request.getDescription());
        module.setOwnerId(request.getOwnerId());
        module.setTags(request.getTags());
        module.setStatus(request.getStatus());
        module.setPriority(request.getPriority());
        module.setOrder(request.getOrder());
        module.setEstimatedEffort(request.getEstimatedEffort());
        module.setCustomFields(request.getCustomFields());
        module.setUpdatedAt(LocalDateTime.now());

        Modules updated = moduleRepository.save(module);
        return GeneralUtility.success("Module updated succesfully", toResponse(updated));
    }

    public GenericResponse<String> deleteModule(String id) {
        moduleRepository.deleteById(id);
        return GeneralUtility.success("Module Deleted Successfully");
    }

    private ModuleResponse toResponse(Modules module) {
        return ModuleResponse.builder()
                .id(module.getId())
                .name(module.getName())
                .description(module.getDescription())
                .projectId(module.getProjectId())
                .ownerId(module.getOwnerId())
                .tags(module.getTags())
                .status(module.getStatus())
                .priority(module.getPriority())
                .order(module.getOrder())
                .estimatedEffort(module.getEstimatedEffort())
                .customFields(module.getCustomFields())
                .createdBy(module.getCreatedBy())
                .createdAt(module.getCreatedAt())
                .updatedAt(module.getUpdatedAt())
                .build();
    }
}

