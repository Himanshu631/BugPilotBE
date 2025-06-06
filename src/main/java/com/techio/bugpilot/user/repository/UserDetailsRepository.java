package com.techio.bugpilot.user.repository;

import com.techio.bugpilot.user.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
}
