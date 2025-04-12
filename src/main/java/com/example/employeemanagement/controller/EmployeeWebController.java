package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/web")
public class EmployeeWebController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeWebController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Display list of employees
    @GetMapping("/")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "index";
    }

    // Show employee creation form
    @GetMapping("/employees/new")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "create-employee";
    }

    // Handle employee creation
    @PostMapping("/employees/new")
    public String createEmployee(@Valid @ModelAttribute("employee") Employee employee,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "create-employee";
        }
        
        try {
            employeeService.createEmployee(employee);
            redirectAttributes.addFlashAttribute("message", "Employee created successfully!");
            return "redirect:/web/";
        } catch (IllegalArgumentException e) {
            result.rejectValue("email", "error.employee", e.getMessage());
            return "create-employee";
        }
    }

    // Show employee edit form
    @GetMapping("/employees/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "edit-employee";
    }

    // Handle employee update
    @PostMapping("/employees/edit/{id}")
    public String updateEmployee(@PathVariable Long id,
                               @Valid @ModelAttribute("employee") Employee employee,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "edit-employee";
        }
        
        try {
            employeeService.updateEmployee(id, employee);
            redirectAttributes.addFlashAttribute("message", "Employee updated successfully!");
            return "redirect:/web/";
        } catch (IllegalArgumentException e) {
            result.rejectValue("email", "error.employee", e.getMessage());
            return "edit-employee";
        }
    }

    // Handle employee deletion
    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            employeeService.deleteEmployee(id);
            redirectAttributes.addFlashAttribute("message", "Employee deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting employee: " + e.getMessage());
        }
        return "redirect:/web/";
    }
} 