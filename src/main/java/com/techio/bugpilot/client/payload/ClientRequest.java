package com.techio.bugpilot.client.payload;

import com.techio.bugpilot.user.payload.UserRequest;
import lombok.Data;

import java.util.Map;

@Data
public class ClientRequest {
    private String organization;          // Organization name
    private String email;                 // Primary contact email
    private String phoneNumber;           // Optional phone number
    private String website;               // Company website
    private String address;               // Physical address
    private String industry;              // E.g., FinTech, Health, EdTech
    private String size;                  // Small, Medium, Enterprise
    private String timezone;              // Preferred timezone
    private String locale;                // Language preference (e.g., en_US)

    private UserRequest userRequest;      // Primary admin user info
    private Map<String, Object> metadata; // For custom fields or dynamic data
}
