package com.cscb525.project.service;

import com.cscb525.project.dto.employee.EmployeeDto;
import com.cscb525.project.dto.employee.EmployeeDtoResponse;
import com.cscb525.project.model.employee.DrivingQualification;
import com.cscb525.project.model.employee.SortingAndFilteringCriteria;
import com.cscb525.project.model.transportCompany.FilterType;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDtoResponse> getAllEmployees(SortingAndFilteringCriteria filterBy, FilterType filterType, DrivingQualification drivingQualification, String salary);

    EmployeeDtoResponse getEmployee(int employeeId);

    EmployeeDtoResponse addEmployee(EmployeeDto employeeDto);
}
