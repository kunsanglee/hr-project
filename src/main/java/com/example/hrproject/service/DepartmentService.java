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

    public List<DepartmentResponse> findDepartmentWithLocation() {
        List<DepartmentResponse> departmentWithLocation = departmentQueryRepository.findDepartmentWithLocation();
        if (departmentWithLocation.isEmpty()) {
            throw new HrApplicationException(ErrorCode.INTERNAL_SERVER_ERROR, "Internal server error Occurs");
        }
        return departmentWithLocation;
    }

    @Transactional
    public void updateSalaryInDepartment(Integer departmentId, Double percent) {
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
    }
}
