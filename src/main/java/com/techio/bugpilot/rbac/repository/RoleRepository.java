package com.techio.bugpilot.rbac.repository;

import com.techio.bugpilot.rbac.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(String admin);
    Optional<Role> findByIdAndClientId(String id, String clientId);
    List<Role> findByIdIn(List<String> ids);
}
