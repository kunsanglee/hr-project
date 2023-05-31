package com.example.hrproject.repository.query;

import com.example.hrproject.response.JobHistoryResponse;
import com.example.hrproject.domain.entity.*;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.hrproject.domain.entity.QDepartment.*;
import static com.example.hrproject.domain.entity.QEmployee.*;
import static com.example.hrproject.domain.entity.QJob.*;
import static com.example.hrproject.domain.entity.QJobHistory.*;

@RequiredArgsConstructor
@Repository
public class EmployeeQueryRepository {

    private final JPAQueryFactory factory;

    public Optional<Employee> findCurrentEmployeeById(Integer employeeId) {
        QEmployee manager = new QEmployee("manager");
        return Optional.ofNullable(factory
                .selectFrom(employee)
                .join(employee.job, job).fetchJoin()
                .leftJoin(employee.manager, manager).fetchJoin()
                .leftJoin(employee.department, department).fetchJoin()
                .where(employee.id.eq(employeeId))
                .fetchOne());
    }


    public List<JobHistoryResponse> findEmployeeHistoryById(Integer employeeId) {
        return factory
                .select(Projections.constructor(JobHistoryResponse.class,
                        jobHistory.employee.id,
                        jobHistory.employee.firstName,
                        jobHistory.employee.lastName,
                        jobHistory.employee.email,
                        jobHistory.employee.phoneNumber,
                        jobHistory.startDate,
                        jobHistory.endDate,
                        jobHistory.job,
                        jobHistory.department))
                .from(jobHistory)
                .leftJoin(jobHistory.employee, employee)
                .leftJoin(jobHistory.job, job)
                .leftJoin(jobHistory.department, department)
                .where(jobHistory.employee.id.eq(employeeId))
                .fetch();
    }
}
