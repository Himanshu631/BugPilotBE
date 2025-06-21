package com.techio.bugpilot.auth.controller;

import com.techio.bugpilot.auth.payload.AuthRequest;
import com.techio.bugpilot.auth.service.AuthService;
import com.techio.bugpilot.utility.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authenticate")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;

    @PostMapping
    public ResponseEntity<GenericResponse<?>> generateToken(@RequestBody AuthRequest authRequest) {
        GenericResponse<?> genericResponse = authService.authenticate(authRequest);
        if (genericResponse.isSuccess()) {
            return new ResponseEntity<>(genericResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(genericResponse, HttpStatus.UNAUTHORIZED);
        }
    }
}
