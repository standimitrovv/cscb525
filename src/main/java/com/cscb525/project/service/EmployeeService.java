package com.cscb525.project.service;

import com.cscb525.project.dto.employee.EmployeeDto;
import com.cscb525.project.dto.employee.EmployeeDtoResponse;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDtoResponse> getAllEmployees();

    EmployeeDtoResponse getEmployee(int employeeId);

    EmployeeDtoResponse addEmployee(EmployeeDto employeeDto);
}
