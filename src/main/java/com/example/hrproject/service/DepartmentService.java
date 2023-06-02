package com.example.hrproject.service;

import com.example.hrproject.domain.dto.EmployeeDto;
import com.example.hrproject.domain.dto.JobDto;
import com.example.hrproject.domain.dto.UpdatedEmployeeDto;
import com.example.hrproject.domain.entity.Job;
import com.example.hrproject.exception.ErrorCode;
import com.example.hrproject.exception.HrApplicationException;
import com.example.hrproject.repository.query.DepartmentQueryRepository;
import com.example.hrproject.response.DepartmentResponse;
import com.example.hrproject.response.DepartmentUpdateSalaryResponse;
import com.example.hrproject.response.Response;
import com.example.hrproject.trace.TraceStatus;
import com.example.hrproject.trace.logtrace.LogTrace;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DepartmentService {

    @PersistenceContext
    private final EntityManager em;
    private final DepartmentQueryRepository departmentQueryRepository;
    private final LogTrace trace;

    public List<DepartmentResponse> findDepartmentWithLocation() {
        List<DepartmentResponse> departmentWithLocation;
        TraceStatus status = null;
        try {
            status = trace.begin("DepartmentService.findDepartmentWithLocation()");
            departmentWithLocation = departmentQueryRepository.findDepartmentWithLocation();
            if (departmentWithLocation.isEmpty()) {
                throw new HrApplicationException(ErrorCode.INTERNAL_SERVER_ERROR, "Internal server error Occurs");
            }

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

        return departmentWithLocation;
    }

    @Transactional
    public void updateSalaryInDepartment(Integer departmentId, Double percent) {
        TraceStatus status = null;
        try {
            status = trace.begin("DepartmentService.updateSalaryInDepartment()");
            long response = departmentQueryRepository.updateSalaryInDepartment(departmentId, percent);
            if (response == 0) {
                throw new HrApplicationException(ErrorCode.DEPARTMENT_NOT_FOUND, "Department not founded");
            }
            em.flush();
            em.clear();

            List<UpdatedEmployeeDto> employeeList = departmentQueryRepository.findEmployeeInDepartment(departmentId);
            departmentQueryRepository.updateJobSalary(employeeList);

            em.flush();
            em.clear();

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
