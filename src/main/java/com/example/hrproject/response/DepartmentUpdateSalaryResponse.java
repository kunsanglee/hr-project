package com.example.hrproject.response;

import com.example.hrproject.domain.dto.EmployeeDto;
import com.example.hrproject.domain.dto.EmployeeManagerDto;
import com.example.hrproject.domain.dto.JobDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DepartmentUpdateSalaryResponse {

    private Integer id;
    private String departmentName;
    private EmployeeManagerDto manager;
    private List<EmployeeDto> employeeList;
    private List<JobDto> jobDto;

}
