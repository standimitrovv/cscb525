package com.cscb525.project.service.implementation;

import com.cscb525.project.dto.employee.EmployeeDto;
import com.cscb525.project.dto.employee.EmployeeDtoResponse;
import com.cscb525.project.model.employee.Employee;
import com.cscb525.project.repository.EmployeeRepository;
import com.cscb525.project.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = new ModelMapper();
    }


    public List<EmployeeDtoResponse> getAllEmployees(){
        List<Employee> employees = this.employeeRepository.findAll();

        return employees
                .stream()
                .map((e) -> this.modelMapper.map(e, EmployeeDtoResponse.class))
                .collect(Collectors.toList());
    }

    public EmployeeDtoResponse getEmployee(int employeeId){
        Employee e = this.employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return this.modelMapper.map(e, EmployeeDtoResponse.class);
    }

    public EmployeeDtoResponse addEmployee(EmployeeDto employeeDto){
        Employee tempEmployee = new Employee();

        tempEmployee.setName(employeeDto.getName());
        tempEmployee.setSalary(employeeDto.getSalary());
        tempEmployee.setDrivingQualification(employeeDto.getDrivingQualification());

        Employee e = this.employeeRepository.save(tempEmployee);

        return this.modelMapper.map(e, EmployeeDtoResponse.class);
    }
}
