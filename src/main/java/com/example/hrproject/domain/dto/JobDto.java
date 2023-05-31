package com.example.hrproject.domain.dto;

import com.example.hrproject.domain.entity.Job;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobDto {

    private String id;
    private String jobTitle;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;

    private JobDto(String id, String jobTitle, BigDecimal minSalary, BigDecimal maxSalary) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    public static JobDto of(Job job) {
        return new JobDto(job.getId(), job.getJobTitle(), job.getMinSalary(), job.getMaxSalary());
    }
}
