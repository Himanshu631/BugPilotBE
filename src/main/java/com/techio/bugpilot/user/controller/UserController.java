package com.techio.bugpilot.user.controller;

import com.techio.bugpilot.user.payload.UserRequest;
import com.techio.bugpilot.user.repository.EmployeeRepository;
import com.techio.bugpilot.user.service.UserService;
import com.techio.bugpilot.utility.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserService userDetailsService;

    private final Map<String, Integer> clientOffsets = new ConcurrentHashMap<>();

    private final int PAGE_SIZE = 5;

//    @GetMapping
//    public EmployeeApiResponse streamEmployees(HttpServletRequest request) {
//        String clientKey = request.getRemoteAddr(); // could be IP or session token
//
//        int currentPage = clientOffsets.getOrDefault(clientKey, 0);
//        Pageable pageable = PageRequest.of(currentPage, PAGE_SIZE);
//        Page<Employee> employeePage = employeeRepository.findAll(pageable);
//
//        boolean isLoadComplete = (currentPage + 1) >= employeePage.getTotalPages();
//
//        // Update offset only if data remains
//        if (!isLoadComplete) {
//            clientOffsets.put(clientKey, currentPage + 1);
//        } else {
//            clientOffsets.remove(clientKey); // Reset if fully loaded
//        }
//
//        return new EmployeeApiResponse(employeePage.getContent(), isLoadComplete);
//    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequest request) {
        GenericResponse<?> genericResponse = userDetailsService.createUser(request);
        if (genericResponse.isSuccess()) {
            return new ResponseEntity<>(genericResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(genericResponse, HttpStatus.UNAUTHORIZED);
        }
    }
}
