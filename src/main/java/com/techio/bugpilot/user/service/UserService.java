package com.techio.bugpilot.user.service;

import com.techio.bugpilot.user.entity.User;
import com.techio.bugpilot.user.payload.UserRequest;
import com.techio.bugpilot.utility.GenericResponse;

import java.util.List;

public interface UserService {
    GenericResponse<User> createUser(UserRequest userRequest);
    GenericResponse<User> assignRoles(String userId, List<String> roleIds);
    GenericResponse<?> getAssignedRoles(String userId);
}
