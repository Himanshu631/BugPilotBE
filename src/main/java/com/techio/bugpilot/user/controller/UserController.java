package com.techio.bugpilot.user.controller;

import com.techio.bugpilot.user.payload.UserRequest;
import com.techio.bugpilot.user.repository.EmployeeRepository;
import com.techio.bugpilot.user.service.UserService;
import com.techio.bugpilot.utility.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserService userDetailsService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequest request) {
        GenericResponse<?> genericResponse = userDetailsService.createUser(request);
        if (genericResponse.isSuccess()) {
            return new ResponseEntity<>(genericResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(genericResponse, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsersByClientId() {
        GenericResponse<?> genericResponse = userDetailsService.getAllUsersByClientId();
        if (genericResponse.isSuccess()) {
            return new ResponseEntity<>(genericResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(genericResponse, HttpStatus.UNAUTHORIZED);
        }
    }
}
