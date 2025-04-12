package com.example.employeemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication(scanBasePackages = "com.example.employeemanagement")
@EntityScan("com.example.employeemanagement.model")
@EnableJpaRepositories("com.example.employeemanagement.repository")
@OpenAPIDefinition(
    info = @Info(
        title = "Employee Management API",
        version = "1.0",
        description = "REST API documentation for Employee Management System"
    )
)
public class EmployeeManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementApplication.class, args);
    }
} 