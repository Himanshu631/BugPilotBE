package com.techio.bugpilot.client.repository;

import com.techio.bugpilot.client.entity.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {
}

