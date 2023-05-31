package com.example.hrproject.domain.dto;

import com.example.hrproject.domain.entity.Employee;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeListDto {

    private Integer id;
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDateTime hireDate;

    public static EmployeeListDto of(Employee e) {
        return new EmployeeListDto(
                e.getId(),
                e.getFirstName() + e.getLastName(),
                e.getEmail(),
                e.getPhoneNumber(),
                e.getHireDate()
        );
    }
}
