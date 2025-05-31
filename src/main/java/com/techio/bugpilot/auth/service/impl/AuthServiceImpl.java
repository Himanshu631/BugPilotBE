package com.techio.bugpilot.auth.service.impl;

import com.techio.bugpilot.auth.payload.AuthRequest;
import com.techio.bugpilot.auth.payload.AuthResponse;
import com.techio.bugpilot.auth.service.AuthService;
import com.techio.bugpilot.user.entity.User;
import com.techio.bugpilot.user.repository.UserDetailsRepository;
import com.techio.bugpilot.utility.GeneralUtility;
import com.techio.bugpilot.utility.GenericResponse;
import com.techio.bugpilot.utility.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsRepository userDetailsRepository;

    @Override
    public GenericResponse<?> authenticate(AuthRequest authRequest) {
        Optional<User> userOptional = userDetailsRepository.findByUsername(authRequest.getUserName());

        if (userOptional.isEmpty()) {
            return GeneralUtility.failure("Invalid username or password");
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (BadCredentialsException ex) {
            return GeneralUtility.failure("Invalid username or password");
        }

        User user = userOptional.get();

        AuthResponse authResponse = new AuthResponse();
        authResponse.setName(user.getName());
        authResponse.setUserName(user.getUsername());
        authResponse.setAccessToken(jwtUtil.generateToken(authRequest.getUserName()));
        authResponse.setMessage("Login successful");

        return GeneralUtility.success("Login successful", authResponse);
    }

}
