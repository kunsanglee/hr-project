package com.example.hrproject.service;

import com.example.hrproject.response.JobHistoryResponse;
import com.example.hrproject.exception.ErrorCode;
import com.example.hrproject.exception.HrApplicationException;
import com.example.hrproject.repository.query.EmployeeQueryRepository;
import com.example.hrproject.response.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EmployeeService {

    private final EmployeeQueryRepository employeeQueryRepository;


    public EmployeeResponse findEmployee(Integer employeeId) {
        return EmployeeResponse.of(employeeQueryRepository.findCurrentEmployeeById(employeeId).orElseThrow(() ->
                new HrApplicationException(ErrorCode.USER_NOT_FOUND, String.format("Employee id %s is not founded", employeeId))));
    }

    public List<JobHistoryResponse> findHistory(Integer employeeId) {
        List<JobHistoryResponse> history = employeeQueryRepository.findEmployeeHistoryById(employeeId);
        if (history.isEmpty()) {
            throw new HrApplicationException(ErrorCode.HISTORY_NOT_FOUND, String.format("Employee %s's history is not founded", employeeId));
        }
        return history;
    }
}
