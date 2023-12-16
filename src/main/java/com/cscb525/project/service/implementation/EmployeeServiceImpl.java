package com.cscb525.project.service.implementation;

import com.cscb525.project.dto.employee.EmployeeDto;
import com.cscb525.project.dto.employee.EmployeeDtoResponse;
import com.cscb525.project.model.employee.DrivingQualification;
import com.cscb525.project.model.employee.Employee;
import com.cscb525.project.model.employee.SortingAndFilteringCriteria;
import com.cscb525.project.model.transportCompany.FilterType;
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


    public List<EmployeeDtoResponse> getAllEmployees(
            SortingAndFilteringCriteria filterBy,
            FilterType filterType,
            DrivingQualification drivingQualification,
            String salary
    ){
        if(filterBy == null || filterBy == SortingAndFilteringCriteria.NONE) {
            return convertToEmployeeDtoResponseList(this.employeeRepository.findAll());
        }

        if(filterBy != SortingAndFilteringCriteria.NONE){

            return switch (filterBy){
                case QUALIFICATION -> {
                    if(drivingQualification == null) {
                        throw new RuntimeException("The 'qualification' field must not be empty");
                    }

                    yield this.convertToEmployeeDtoResponseList(
                            this.employeeRepository.getAllEmployeesWithDrivingQualification(drivingQualification)
                    );
                }
                case SALARY -> {
                    if(salary.isEmpty()){
                        throw new RuntimeException("The 'salary' field must not be empty");
                    }

                    double parsedSalary = Double.parseDouble(salary);

                    yield switch (filterType){
                        case EQ -> convertToEmployeeDtoResponseList(
                                this.employeeRepository.getAllEmployeesWithSalaryEQTo(parsedSalary)
                        );
                        case LT -> convertToEmployeeDtoResponseList(
                                this.employeeRepository.getAllEmployeesWithSalaryLessThan(parsedSalary)
                        );
                        case LTOEQ -> convertToEmployeeDtoResponseList(
                                this.employeeRepository.getAllEmployeesWithSalaryLessThanOrEQTo(parsedSalary)
                        );
                        case MT -> convertToEmployeeDtoResponseList(
                                this.employeeRepository.getAllEmployeesWithSalaryMoreThan(parsedSalary)
                        );
                        case MTOEQ -> convertToEmployeeDtoResponseList(
                                this.employeeRepository.getAllEmployeesWithSalaryMoreThanOrEQTo(parsedSalary)
                        );
                    };
                }
                case NONE -> null;
            };
        }

        return convertToEmployeeDtoResponseList(this.employeeRepository.findAll());
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

    private List<EmployeeDtoResponse> convertToEmployeeDtoResponseList(List<Employee> employeeList){
        return employeeList
                .stream()
                .map(e -> this.modelMapper.map(e, EmployeeDtoResponse.class))
                .collect(Collectors.toList());
    }
}
