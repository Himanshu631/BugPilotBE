package com.techio.bugpilot.utility;

import com.techio.bugpilot.user.payload.UserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class AuthContextUtil {

    public String getClientIdOrThrow() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth.getPrincipal() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid authentication context");
        }

        if (auth.getPrincipal() instanceof UserPrincipal userPrincipal) {
            String clientId = userPrincipal.getClientId();
            if (clientId == null || clientId.isBlank()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client ID is missing in token");
            }
            return clientId;
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unexpected authentication principal");
    }

    public String getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth.getPrincipal() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid authentication context");
        }

        if (auth.getPrincipal() instanceof UserPrincipal userPrincipal) {
            String userId = userPrincipal.getUserId();
            if (userId == null || userId.isBlank()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User ID is missing in token");
            }
            return userId;
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unexpected authentication principal");
    }
}

