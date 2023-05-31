package com.example.hrproject.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class JobHistoryId implements Serializable {

    private Integer employeeId;

    private String jobId;

    private Integer departmentId;

    public JobHistoryId(Integer employeeId, String jobId, Integer departmentId) {
        this.employeeId = employeeId;
        this.jobId = jobId;
        this.departmentId = departmentId;
    }
}
