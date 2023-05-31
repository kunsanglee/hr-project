package com.example.hrproject.response;

import com.example.hrproject.domain.dto.DepartmentDto;
import com.example.hrproject.domain.dto.JobDto;
import com.example.hrproject.domain.entity.Department;
import com.example.hrproject.domain.entity.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class JobHistoryResponse {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private JobDto job;
    private DepartmentDto department;

    public JobHistoryResponse(Integer id, String firstName, String lastName, String email, String phoneNumber, LocalDateTime startDate, LocalDateTime endDate, Job job, Department department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.job = JobDto.of(job);
        this.department = DepartmentDto.of(department);
    }
}
