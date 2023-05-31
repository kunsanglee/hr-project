package com.example.hrproject.repository.query;

import com.example.hrproject.domain.dto.JobDto;
import com.example.hrproject.domain.dto.UpdatedEmployeeDto;
import com.example.hrproject.response.DepartmentResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.hrproject.domain.entity.QCountry.*;
import static com.example.hrproject.domain.entity.QDepartment.department;
import static com.example.hrproject.domain.entity.QEmployee.*;
import static com.example.hrproject.domain.entity.QJob.*;
import static com.example.hrproject.domain.entity.QLocation.*;
import static com.example.hrproject.domain.entity.QRegion.*;
import static java.util.Collections.min;

@RequiredArgsConstructor
@Repository
public class DepartmentQueryRepository {

    private final JPAQueryFactory factory;


    public List<DepartmentResponse> findDepartmentWithLocation() {
        return factory
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
    }

    public List<UpdatedEmployeeDto> findEmployeeInDepartment(Integer departmentId) {
        return factory
                .select(Projections.constructor(UpdatedEmployeeDto.class,
                        employee.id,
                        employee.salary,
                        employee.job
                        ))
                .from(employee)
                .where(employee.department.id.eq(departmentId))
                .fetch();
    }



    public long updateSalaryInDepartment(Integer departmentId, Double percent) {
        return factory
                .update(employee)
                .set(employee.salary, employee.salary.multiply(percent))
                .where(employee.department.id.eq(departmentId))
                .execute();
    }

    public void updateJobSalary(List<UpdatedEmployeeDto> employeeList) {
        for (UpdatedEmployeeDto e : employeeList) {
            if (e.getSalary().compareTo(e.getJob().getMinSalary()) < 0) {
                factory
                        .update(job)
                        .set(job.minSalary, e.getSalary())
                        .where(job.id.eq(e.getJob().getId()))
                        .execute();
            }
            if (e.getSalary().compareTo(e.getJob().getMaxSalary()) > 0) {
                factory
                        .update(job)
                        .set(job.maxSalary, e.getSalary())
                        .where(job.id.eq(e.getJob().getId()))
                        .execute();
            }
        }
    }
}
