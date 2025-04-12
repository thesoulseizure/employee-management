package com.example.employeemanagement.service;

import com.example.employeemanagement.model.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    
    Employee getEmployeeById(Long id);
    
    Employee createEmployee(Employee employee);
    
    Employee updateEmployee(Long id, Employee employeeDetails);
    
    void deleteEmployee(Long id);
    
    List<Employee> getEmployeesByDepartment(String department);
    
    List<Employee> getEmployeesByPosition(String position);
} 