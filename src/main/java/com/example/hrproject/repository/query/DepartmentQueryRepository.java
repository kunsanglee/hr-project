package com.example.hrproject.repository.query;

import com.example.hrproject.domain.dto.JobDto;
import com.example.hrproject.domain.dto.UpdatedEmployeeDto;
import com.example.hrproject.domain.entity.Employee;
import com.example.hrproject.exception.ErrorCode;
import com.example.hrproject.exception.HrApplicationException;
import com.example.hrproject.response.DepartmentResponse;
import com.example.hrproject.trace.TraceStatus;
import com.example.hrproject.trace.logtrace.LogTrace;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.hrproject.domain.entity.QCountry.*;
import static com.example.hrproject.domain.entity.QDepartment.department;
import static com.example.hrproject.domain.entity.QEmployee.*;
import static com.example.hrproject.domain.entity.QJob.*;
import static com.example.hrproject.domain.entity.QLocation.*;
import static com.example.hrproject.domain.entity.QRegion.*;
import static java.util.Collections.max;
import static java.util.Collections.min;

@RequiredArgsConstructor
@Repository
public class DepartmentQueryRepository {

    private final JPAQueryFactory factory;
    private final LogTrace trace;


    public List<DepartmentResponse> findDepartmentWithLocation() {
        List<DepartmentResponse> result;
        TraceStatus status = null;

        try {
            status = trace.begin("DepartmentQueryRepository.findDepartmentWithLocation()");

            result = factory
                    .select(Projections.constructor(DepartmentResponse.class,
                            department.id,
                            department.departmentName,
                            department.manager,
                            department.location
                    ))
                    .from(department)
                    .join(department.location, location)
                    .join(location.country, country)
                    .join(country.region, region)
                    .fetch();

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

        return result;
    }

    public List<UpdatedEmployeeDto> findEmployeeInDepartment(Integer departmentId) {
        List<UpdatedEmployeeDto> result;
        TraceStatus status = null;

        try {
            status = trace.begin("DepartmentQueryRepository.findEmployeeInDepartment()");

            result = factory
                    .select(Projections.constructor(UpdatedEmployeeDto.class,
                            employee.id,
                            employee.salary,
                            employee.job
                    ))
                    .from(employee)
                    .where(employee.department.id.eq(departmentId))
                    .fetch();

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }


        return result;
    }



    public long updateSalaryInDepartment(Integer departmentId, Double percent) {
        long result;
        TraceStatus status = null;
        try {
            status = trace.begin("DepartmentQueryRepository.updateSalaryInDepartment()");

            result = factory
                    .update(employee)
                    .set(employee.salary, employee.salary.multiply(percent))
                    .where(employee.department.id.eq(departmentId))
                    .execute();

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }


        return result;
    }

    public void updateJobSalary(List<UpdatedEmployeeDto> employeeList) {

        TraceStatus status = null;
        try {
            status = trace.begin("DepartmentQueryRepository.updateJobSalary()");

            HashSet<String> set = new HashSet<>();
            for (UpdatedEmployeeDto e : employeeList) {
                set.add(e.getJob().getId());
            }
            for (String jobId : set) {
                List<Employee> findEmployeeList = factory
                        .select(employee)
                        .from(employee)
                        .join(employee.job, job).fetchJoin()
                        .where(employee.job.id.eq(jobId))
                        .fetch();

                BigDecimal minSalary = findEmployeeList.stream().min(Comparator.comparing(Employee::getSalary)).orElseThrow(() ->
                        new HrApplicationException(ErrorCode.BAD_REQUEST_ERROR, "Server received bad request")).getSalary();
                BigDecimal maxSalary = findEmployeeList.stream().max(Comparator.comparing(Employee::getSalary)).orElseThrow(() ->
                        new HrApplicationException(ErrorCode.BAD_REQUEST_ERROR, "Server received bad request")).getSalary();

                factory
                        .update(job)
                        .set(job.minSalary, minSalary)
                        .set(job.maxSalary, maxSalary)
                        .where(job.id.eq(jobId))
                        .execute();
            }

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
