package com.example.hrproject.domain.dto;

import com.example.hrproject.domain.entity.Department;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DepartmentDto {

    private Integer id;
    private String departmentName;


    public static DepartmentDto of(Department d) {
        if (d == null) {
            return new DepartmentDto();
        }
        return new DepartmentDto(
                d.getId(),
                d.getDepartmentName()
        );
    }
}
