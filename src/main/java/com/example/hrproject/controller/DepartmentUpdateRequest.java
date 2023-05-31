package com.example.hrproject.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DepartmentUpdateRequest {

    private Integer departmentId;
    private Double percent;
}
