package com.techio.bugpilot.rbac.repository;

import com.techio.bugpilot.rbac.entity.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PermissionRepository extends MongoRepository<Permission, String> {
    Optional<Permission> findByName(String perm);
}
