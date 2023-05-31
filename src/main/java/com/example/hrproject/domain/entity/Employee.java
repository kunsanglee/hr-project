package com.example.hrproject.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "employees")
@Entity
public class Employee {

    @Id
    @Column(name = "employee_id")
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private LocalDateTime hireDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(precision = 8, scale = 2)
    private BigDecimal salary;

    @Column(precision = 2, scale = 2)
    private BigDecimal commissionPct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @OneToMany(mappedBy = "manager")
    private List<Employee> employeeList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    private Employee(String firstName, String lastName, String email, String phoneNumber, LocalDateTime hireDate, Job job, BigDecimal salary, BigDecimal commissionPct, Employee manager, List<Employee> employeeList, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.job = job;
        this.salary = salary;
        this.commissionPct = commissionPct;
        this.manager = manager;
        this.employeeList = employeeList;
        this.department = department;
    }

    public Employee(String first, String last) {
        this.firstName = first;
        this.lastName = last;
    }

    public Employee of(String firstName, String lastName, String email, String phoneNumber, LocalDateTime hireDate, Job job, BigDecimal salary, BigDecimal commissionPct, Employee manager, List<Employee> employeeList, Department department) {
        return new Employee(firstName, lastName, email, phoneNumber, hireDate, job, salary, commissionPct, manager, employeeList, department);
    }

}
