package com.techio.bugpilot.user.repository;

import com.techio.bugpilot.user.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    Page<Employee> findAll(Pageable pageable);
}

