package com.example.hrproject.domain.dto;

import com.example.hrproject.domain.entity.Employee;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeDto {

    private Integer id;
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDateTime hireDate;
    private BigDecimal salary;

    public static EmployeeDto of(Employee e) {
        return new EmployeeDto(
                e.getId(),
                e.getFirstName() + e.getLastName(),
                e.getEmail(),
                e.getPhoneNumber(),
                e.getHireDate(),
                e.getSalary()
        );
    }
}
