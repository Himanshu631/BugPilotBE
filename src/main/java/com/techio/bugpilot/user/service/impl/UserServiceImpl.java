package com.techio.bugpilot.user.service.impl;

import com.techio.bugpilot.config.AppConfig;
import com.techio.bugpilot.user.entity.User;
import com.techio.bugpilot.user.payload.UserRequest;
import com.techio.bugpilot.user.repository.UserDetailsRepository;
import com.techio.bugpilot.user.service.UserService;
import com.techio.bugpilot.utility.GeneralUtility;
import com.techio.bugpilot.utility.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDetailsRepository userRepository;

    @Autowired
    private AppConfig appConfig;

    public GenericResponse<User> createUser(UserRequest userRequest) {
        if (userRequest.getUsername() == null ) {
            return GeneralUtility.failure("Username cannot be null");
        }
        if(userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
            return GeneralUtility.failure("Username already exists");
        }

        User user = new User();
        user.setName(userRequest.getName());
        user.setUsername(userRequest.getUsername());
        user.setPassword(appConfig.passwordEncoder().encode(userRequest.getPassword()));

        userRepository.save(user);

        return GeneralUtility.success("User Created Successfully", user);
    }

    @Override
    public GenericResponse<User> assignRoles(String userId, List<String> roleIds) {
        return null;
    }

    @Override
    public GenericResponse<?> getAssignedRoles(String userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return GeneralUtility.failure("Invalid User Id");
        }

        User user = userOptional.get();

        return GeneralUtility.success("User Details Fetched Successfully", user);
    }
}
