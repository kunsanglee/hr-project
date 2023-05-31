package com.example.hrproject.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "jobs")
@Entity
public class Job {

    @Id
    @Column(name = "job_id")
    private String id;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "min_salary", precision = 8)
    private BigDecimal minSalary;

    @Column(name = "max_salary", precision = 8)
    private BigDecimal maxSalary;

    public Job(String jobTitle, BigDecimal minSalary, BigDecimal maxSalary) {
        this.jobTitle = jobTitle;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    private Job of(String jobTitle, BigDecimal minSalary, BigDecimal maxSalary) {
        return new Job(jobTitle, minSalary, maxSalary);
    }
}
