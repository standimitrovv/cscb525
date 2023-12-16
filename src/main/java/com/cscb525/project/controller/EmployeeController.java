package com.cscb525.project.controller;

import com.cscb525.project.dto.employee.EmployeeDto;
import com.cscb525.project.dto.employee.EmployeeDtoResponse;
import com.cscb525.project.model.employee.DrivingQualification;
import com.cscb525.project.model.employee.SortType;
import com.cscb525.project.model.employee.SortingAndFilteringCriteria;
import com.cscb525.project.model.transportCompany.FilterType;
import com.cscb525.project.service.implementation.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDtoResponse> getAllEmployees(
            @RequestParam(name = "filterBy", required = false, defaultValue = "NONE") SortingAndFilteringCriteria filterBy,
            @RequestParam(name = "filterType", required = false, defaultValue = "EQ") FilterType filterType,
            @RequestParam(name = "sortBy", required = false, defaultValue = "NONE") SortingAndFilteringCriteria sortBy,
            @RequestParam(name = "sortType", required = false, defaultValue = "ASC") SortType sortType,
            @RequestParam(name = "qualification", required = false, defaultValue = "") DrivingQualification drivingQualification,
            @RequestParam(name = "salary", required = false, defaultValue = "") String salary
    ){
        return this.employeeService.getAllEmployees(
                filterBy,
                filterType,
                sortBy,
                sortType,
                drivingQualification,
                salary
        );
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
