package com.example.hrproject.domain.dto;

import com.example.hrproject.domain.entity.Job;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdatedEmployeeDto {

    private Integer id;
    private BigDecimal salary;
    private JobDto job;

    public UpdatedEmployeeDto(Integer id, BigDecimal salary, Job job) {
        this.id = id;
        this.salary = salary;
        this.job = JobDto.of(job);
    }
}
