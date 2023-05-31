package com.example.hrproject.response;

import com.example.hrproject.domain.dto.DepartmentDto;
import com.example.hrproject.domain.dto.EmployeeDto;
import com.example.hrproject.domain.dto.EmployeeManagerDto;
import com.example.hrproject.domain.dto.JobDto;
import com.example.hrproject.domain.entity.Employee;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EmployeeResponse {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDateTime hireDate;
    private JobDto job;
    private BigDecimal salary;
    private BigDecimal commissionPct;
    private EmployeeManagerDto manager;
    private List<EmployeeDto> employeeList = new ArrayList<>();
    private DepartmentDto department;

    public static EmployeeResponse of(Employee e) {
        EmployeeResponse response = new EmployeeResponse();
        response.id = e.getId();
        response.firstName = e.getFirstName();
        response.lastName = e.getLastName();
        response.email = e.getEmail();
        response.phoneNumber = e.getPhoneNumber();
        response.hireDate = e.getHireDate();
        response.job = JobDto.of(e.getJob());
        response.salary = e.getSalary();
        response.commissionPct = e.getCommissionPct();
        response.manager = EmployeeManagerDto.of(e.getManager());
        response.employeeList = e.getEmployeeList().stream().map(EmployeeDto::of).collect(Collectors.toList());
        response.department = DepartmentDto.of(e.getDepartment());
        return response;
    }

}
