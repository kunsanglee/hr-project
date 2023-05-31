package com.example.hrproject.domain.dto;

import com.example.hrproject.domain.entity.Job;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobDto {

    private String id;
    private String jobTitle;

    private JobDto(String id, String jobTitle) {
        this.id = id;
        this.jobTitle = jobTitle;
    }

    public static JobDto of(Job job) {
        return new JobDto(job.getId(), job.getJobTitle());
    }
}
