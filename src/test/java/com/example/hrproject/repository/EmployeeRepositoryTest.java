//package com.example.hrproject.repository;
//
//import com.example.hrproject.domain.entity.Employee;
//import com.example.hrproject.repository.query.EmployeeQueryRepository;
//import org.junit.jupiter.api.Test;
//import org.mockito.BDDMockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.BDDMockito.*;
//
//@SpringBootTest
//class EmployeeRepositoryTest {
//
//    @Autowired
//    EmployeeQueryRepository employeeQueryRepository;
//
//    @Test
//    public void test() throws Exception {
//
//        given(employeeQueryRepository.findByIdFetchJoin(anyInt())).willReturn(Optional.of(any(Employee.class)));
//
//    }
//
//}