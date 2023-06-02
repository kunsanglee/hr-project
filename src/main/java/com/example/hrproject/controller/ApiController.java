package com.example.hrproject.controller;

import com.example.hrproject.exception.ErrorCode;
import com.example.hrproject.exception.HrApplicationException;
import com.example.hrproject.repository.query.DepartmentQueryRepository;
import com.example.hrproject.response.*;
import com.example.hrproject.service.DepartmentService;
import com.example.hrproject.service.EmployeeService;
import com.example.hrproject.trace.TraceStatus;
import com.example.hrproject.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class ApiController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final LogTrace trace;

    @GetMapping("/employees/{employeeId}")
    public Response<EmployeeResponse> findEmployee(@PathVariable Integer employeeId) {
        Response<EmployeeResponse> success;
        TraceStatus status = null;

        try {
            status = trace.begin("ApiController.findEmployee()");
            success = Response.success(employeeService.findEmployee(employeeId));
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

        trace.end(status);
        return success;
    }

    @GetMapping("/employees/{employeeId}/history")
    public Response<List<JobHistoryResponse>> findHistory(@PathVariable Integer employeeId) {
        Response<List<JobHistoryResponse>> success;
        TraceStatus status = null;

        try {
            status = trace.begin("ApiController.findHistory()");
            success = Response.success(employeeService.findHistory(employeeId));
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

        trace.end(status);
        return success;
    }

    @GetMapping("/departments")
    public Response<List<DepartmentResponse>> findDepartmentWithLocation() {
        Response<List<DepartmentResponse>> success;
        TraceStatus status = null;
        try {
            status = trace.begin("ApiController.findDepartmentWithLocation()");
            success = Response.success(departmentService.findDepartmentWithLocation());
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

        trace.end(status);
        return success;
    }

    @PatchMapping("/departments")
    public Response<Void> departmentUpdateSalary(@RequestBody DepartmentUpdateRequest request) {
        TraceStatus status = null;
        try {
            status = trace.begin("ApiController.departmentUpdateSalary()");
            if (request.getPercent() == 0) {
                throw new HrApplicationException(ErrorCode.BAD_REQUEST_ERROR, "Server received bad request");
            }
            departmentService.updateSalaryInDepartment(request.getDepartmentId(), request.getPercent() / 100 + 1);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

        trace.end(status);
        return Response.success();
    }
}
