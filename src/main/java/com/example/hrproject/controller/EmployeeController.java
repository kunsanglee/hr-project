package com.example.hrproject.controller;

import com.example.hrproject.response.JobHistoryResponse;
import com.example.hrproject.response.EmployeeResponse;
import com.example.hrproject.response.Response;
import com.example.hrproject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/employees")
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{employeeId}")
    public Response<EmployeeResponse> findEmployee(@PathVariable Integer employeeId) {
        return Response.success(employeeService.findEmployee(employeeId));
    }

    @GetMapping("/{employeeId}/history")
    public Response<List<JobHistoryResponse>> findHistory(@PathVariable Integer employeeId) {
        return Response.success(employeeService.findHistory(employeeId));
    }
}
