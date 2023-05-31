package com.example.hrproject.response;

import com.example.hrproject.domain.dto.EmployeeManagerDto;
import com.example.hrproject.domain.dto.LocationDto;
import com.example.hrproject.domain.entity.Employee;
import com.example.hrproject.domain.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class DepartmentResponse {

    private Integer id;
    private String departmentName;
    private EmployeeManagerDto manager;
    private LocationDto location;

    public DepartmentResponse(Integer id, String departmentName, Employee employee, Location location) {
        this.id = id;
        this.departmentName = departmentName;
        this.manager = EmployeeManagerDto.of(employee);
        this.location = LocationDto.of(location);
    }
}
