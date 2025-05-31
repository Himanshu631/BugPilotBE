package com.techio.bugpilot.auth.service;

import com.techio.bugpilot.auth.payload.AuthRequest;
import com.techio.bugpilot.utility.GenericResponse;

public interface AuthService {
    GenericResponse<?> authenticate(AuthRequest authRequest);
}
