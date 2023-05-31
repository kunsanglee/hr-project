package com.example.hrproject.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "departments")
@Entity
public class Department {

    @Id
    @Column(name = "department_id")
    private Integer id;

    private String departmentName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    private Department(String departmentName, Employee manager, Location location) {
        this.departmentName = departmentName;
        this.manager = manager;
        this.location = location;
    }

    public Department of(String departmentName, Employee manager, Location location) {
        return new Department(departmentName, manager, location);
    }
}
