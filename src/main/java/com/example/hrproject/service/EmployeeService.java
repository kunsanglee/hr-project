package com.example.hrproject.service;

import com.example.hrproject.domain.entity.Employee;
import com.example.hrproject.response.JobHistoryResponse;
import com.example.hrproject.exception.ErrorCode;
import com.example.hrproject.exception.HrApplicationException;
import com.example.hrproject.repository.query.EmployeeQueryRepository;
import com.example.hrproject.response.EmployeeResponse;
import com.example.hrproject.trace.TraceStatus;
import com.example.hrproject.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EmployeeService {

    private final EmployeeQueryRepository employeeQueryRepository;
    private final LogTrace trace;


    public EmployeeResponse findEmployee(Integer employeeId) {
        Employee findEmployee;
        TraceStatus status = null;
        try {
            status = trace.begin("EmployeeService.findEmployee()");
            findEmployee = employeeQueryRepository.findCurrentEmployeeById(employeeId).orElseThrow(() ->
                    new HrApplicationException(ErrorCode.USER_NOT_FOUND, String.format("Employee id %s is not founded", employeeId)));
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
        EmployeeResponse result = EmployeeResponse.of(findEmployee);
        trace.end(status);
        return result;
    }

    public List<JobHistoryResponse> findHistory(Integer employeeId) {
        List<JobHistoryResponse> history;
        TraceStatus status = null;

        try {
            status = trace.begin("EmployeeService.findHistory()");
            history = employeeQueryRepository.findEmployeeHistoryById(employeeId);
            if (history.isEmpty()) {
                throw new HrApplicationException(ErrorCode.HISTORY_NOT_FOUND, String.format("Employee %s's history is not founded", employeeId));
            }
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

        trace.end(status);
        return history;
    }
}
