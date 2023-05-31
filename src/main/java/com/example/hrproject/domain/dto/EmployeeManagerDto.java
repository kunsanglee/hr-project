package com.example.hrproject.domain.dto;

import com.example.hrproject.domain.entity.Employee;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeManagerDto {


    private Integer id;
    private String name;
    private String email;
    private String phoneNumber;

    public static EmployeeManagerDto of(Employee e) {
        if (e == null) {
            return new EmployeeManagerDto();
        }
        return new EmployeeManagerDto(e.getId(),
                e.getFirstName() + e.getLastName(),
                e.getEmail(),
                e.getPhoneNumber()
        );
    }
}
