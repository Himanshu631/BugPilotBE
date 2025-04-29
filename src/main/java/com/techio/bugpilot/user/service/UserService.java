package com.techio.bugpilot.user.service;

import com.techio.bugpilot.config.AppConfig;
import com.techio.bugpilot.config.SecurityConfig;
import com.techio.bugpilot.user.entity.User;
import com.techio.bugpilot.user.payload.UserRequest;
import com.techio.bugpilot.user.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDetailsRepository userRepository;

    @Autowired
    private AppConfig appConfig;

    public ResponseEntity<?> createUser(UserRequest userRequest) {

        if (userRequest.getUsername() == null ) {
            return new ResponseEntity<>("Username cannot be null", HttpStatus.BAD_REQUEST);
        }
        if(userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(userRequest.getName());
        user.setUsername(userRequest.getUsername());
        user.setPassword(appConfig.passwordEncoder().encode(userRequest.getPassword()));
        user.setRole(userRequest.getRole());

        userRepository.save(user);

        return new ResponseEntity<>("User Created Successfully", HttpStatus.CREATED);
    }
}
