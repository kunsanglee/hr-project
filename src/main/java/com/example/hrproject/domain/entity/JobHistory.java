package com.example.hrproject.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "job_history")
@Entity
public class JobHistory {

    @EmbeddedId
    private JobHistoryId jobHistoryId;

    @MapsId("employeeId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @MapsId("jobId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    @MapsId("departmentId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public JobHistory(JobHistoryId jobHistoryId, Employee employee, Job job, Department department) {
        this.jobHistoryId = jobHistoryId;
        this.employee = employee;
        this.job = job;
        this.department = department;
    }

}
