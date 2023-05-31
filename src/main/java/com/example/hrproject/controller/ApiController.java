package com.example.hrproject.controller;

import com.example.hrproject.exception.ErrorCode;
import com.example.hrproject.exception.HrApplicationException;
import com.example.hrproject.repository.query.DepartmentQueryRepository;
import com.example.hrproject.response.*;
import com.example.hrproject.service.DepartmentService;
import com.example.hrproject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class ApiController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @GetMapping("/employee/{employeeId}")
    public Response<EmployeeResponse> findEmployee(@PathVariable Integer employeeId) {
        return Response.success(employeeService.findEmployee(employeeId));
    }

    @GetMapping("/employee/{employeeId}/history")
    public Response<List<JobHistoryResponse>> findHistory(@PathVariable Integer employeeId) {
        return Response.success(employeeService.findHistory(employeeId));
    }

    @GetMapping("/departments")
    public Response<List<DepartmentResponse>> findDepartmentWithLocation() {
        return Response.success(departmentService.findDepartmentWithLocation());
    }

    @PatchMapping("/departments")
    public Response<Void> departmentUpdateSalary(@RequestBody DepartmentUpdateRequest request) {
        if (request.getPercent() == 0) {
            throw new HrApplicationException(ErrorCode.BAD_REQUEST_ERROR, "Server received bad request");
        }
        departmentService.updateSalaryInDepartment(request.getDepartmentId(), request.getPercent()/100+1);
        return Response.success();
    }
}
