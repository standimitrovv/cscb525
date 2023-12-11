package com.cscb525.project.service;

import com.cscb525.project.dto.EmployeeDto;
import com.cscb525.project.dto.EmployeeDtoResponse;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDtoResponse> getEmployees();

    EmployeeDtoResponse getEmployee(int employeeId);

    EmployeeDtoResponse addEmployee(EmployeeDto employeeDto);
}
