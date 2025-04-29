package com.techio.bugpilot.user.payload;

import com.techio.bugpilot.user.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeApiResponse {
    private List<Employee> employees;
    private boolean isLoadComplete;

    public EmployeeApiResponse(List<Employee> employees, boolean isLoadComplete) {
        this.employees = employees;
        this.isLoadComplete = isLoadComplete;
    }
}

