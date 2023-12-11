package com.cscb525.project.controller;

import com.cscb525.project.dto.EmployeeDto;
import com.cscb525.project.dto.EmployeeDtoResponse;
import com.cscb525.project.service.implementation.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDtoResponse> getEmployees(){
        return this.employeeService.getEmployees();
    }

    @GetMapping("/{employeeId}")
    public EmployeeDtoResponse getEmployee(@PathVariable int employeeId){
        return this.employeeService.getEmployee(employeeId);
    }

    @PostMapping
    public EmployeeDtoResponse addEmployee(@RequestBody EmployeeDto employeeDto){
        return this.employeeService.addEmployee(employeeDto);
    }
}
