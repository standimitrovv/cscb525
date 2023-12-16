package com.cscb525.project.service.implementation;

import com.cscb525.project.dto.employee.EmployeeDto;
import com.cscb525.project.dto.employee.EmployeeDtoResponse;
import com.cscb525.project.exception.employee.EmployeeNotFoundException;
import com.cscb525.project.model.employee.DrivingQualification;
import com.cscb525.project.model.employee.Employee;
import com.cscb525.project.model.employee.SortType;
import com.cscb525.project.model.employee.SortingAndFilteringCriteria;
import com.cscb525.project.model.transportCompany.FilterType;
import com.cscb525.project.repository.EmployeeRepository;
import com.cscb525.project.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static com.cscb525.project.exception.ExceptionTextMessages.EMPLOYEE_NOT_FOUND;

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
            SortingAndFilteringCriteria sortBy,
            SortType sortType,
            DrivingQualification drivingQualification,
            String salary
    ){
        if((filterBy == SortingAndFilteringCriteria.SALARY || sortBy == SortingAndFilteringCriteria.SALARY) && salary.isEmpty()){
            throw new RuntimeException("The 'salary' field must not be empty");
        }

        if((filterBy == SortingAndFilteringCriteria.QUALIFICATION || sortBy == SortingAndFilteringCriteria.QUALIFICATION) && drivingQualification == null){
            throw new RuntimeException("The 'qualification' field must not be empty");
        }

        Sort.Direction sortDirection = Sort.Direction.ASC;

        if(sortType == SortType.DESC){
            sortDirection = Sort.Direction.DESC;
        }

        // Filtering AND Sorting at the same time
        if(filterBy != SortingAndFilteringCriteria.NONE && sortBy != SortingAndFilteringCriteria.NONE) {
            final double parsedSalary = Double.parseDouble(salary);

            return switch(filterBy){
                case QUALIFICATION ->
                        this.convertToEmployeeDtoResponseList(
                            this.employeeRepository.getAllEmployeesWithDrivingQualification(drivingQualification, Sort.by(sortDirection, "drivingQualification"))
                        );
                case SALARY ->
                        switch (filterType){
                            case EQ -> this.convertToEmployeeDtoResponseList(
                                    this.employeeRepository.getAllEmployeesWithSalaryEQTo(parsedSalary, Sort.by(sortDirection, "salary"))
                            );
                            case LT -> this.convertToEmployeeDtoResponseList(
                                    this.employeeRepository.getAllEmployeesWithSalaryLessThan(parsedSalary, Sort.by(sortDirection, "salary"))
                            );
                            case LTOEQ -> this.convertToEmployeeDtoResponseList(
                                    this.employeeRepository.getAllEmployeesWithSalaryLessThanOrEQTo(parsedSalary, Sort.by(sortDirection, "salary"))
                            );
                            case MT -> this.convertToEmployeeDtoResponseList(
                                    this.employeeRepository.getAllEmployeesWithSalaryMoreThan(parsedSalary, Sort.by(sortDirection, "salary"))
                            );
                            case MTOEQ -> this.convertToEmployeeDtoResponseList(
                                    this.employeeRepository.getAllEmployeesWithSalaryMoreThanOrEQTo(parsedSalary, Sort.by(sortDirection, "salary"))
                            );
                        };
                default -> throw new IllegalStateException("Unexpected value: " + filterBy);
            };
        }

        // Filtering ONLY
        if(filterBy != SortingAndFilteringCriteria.NONE){
            final double parsedSalary = Double.parseDouble(salary);

            return switch (filterBy){
                case QUALIFICATION ->
                        this.convertToEmployeeDtoResponseList(
                            this.employeeRepository.getAllEmployeesWithDrivingQualification(drivingQualification, null)
                        );
                case SALARY ->
                        switch (filterType){
                            case EQ -> this.convertToEmployeeDtoResponseList(
                                    this.employeeRepository.getAllEmployeesWithSalaryEQTo(parsedSalary, null)
                            );
                            case LT -> this.convertToEmployeeDtoResponseList(
                                    this.employeeRepository.getAllEmployeesWithSalaryLessThan(parsedSalary, null)
                            );
                            case LTOEQ -> this.convertToEmployeeDtoResponseList(
                                    this.employeeRepository.getAllEmployeesWithSalaryLessThanOrEQTo(parsedSalary, null)
                            );
                            case MT -> this.convertToEmployeeDtoResponseList(
                                    this.employeeRepository.getAllEmployeesWithSalaryMoreThan(parsedSalary, null)
                            );
                            case MTOEQ -> this.convertToEmployeeDtoResponseList(
                                    this.employeeRepository.getAllEmployeesWithSalaryMoreThanOrEQTo(parsedSalary, null)
                            );
                        };
                default -> throw new IllegalStateException("Unexpected FilterBy value: " + filterBy);
            };
        }

        // Sorting ONLY
        if(sortBy != SortingAndFilteringCriteria.NONE){

            return switch (sortBy){
                case QUALIFICATION -> this.convertToEmployeeDtoResponseList(
                            this.employeeRepository.getAllEmployeesOrderedByQualification(Sort.by(sortDirection, "drivingQualification"))
                );
                case SALARY -> this.convertToEmployeeDtoResponseList(
                            this.employeeRepository.getAllEmployeesOrderedBySalary(Sort.by(sortDirection, "salary"))
                );
                default -> throw new IllegalStateException("Unexpected SortBy value: " + sortBy);
            };
        }

        return this.convertToEmployeeDtoResponseList(this.employeeRepository.findAll());
    }

    public EmployeeDtoResponse getEmployee(int employeeId){
        Employee e = this.employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND));

        return this.convertToEmployeeDtoResponse(e);
    }

    public EmployeeDtoResponse addEmployee(EmployeeDto employeeDto){
        Employee tempEmployee = new Employee();

        tempEmployee.setName(employeeDto.getName());
        tempEmployee.setSalary(employeeDto.getSalary());
        tempEmployee.setDrivingQualification(employeeDto.getDrivingQualification());

        return this.convertToEmployeeDtoResponse(this.employeeRepository.save(tempEmployee));
    }

    private List<EmployeeDtoResponse> convertToEmployeeDtoResponseList(List<Employee> employeeList){
        return employeeList
                .stream()
                .map(this::convertToEmployeeDtoResponse)
                .collect(Collectors.toList());
    }

    private EmployeeDtoResponse convertToEmployeeDtoResponse(Employee employee){
        return this.modelMapper.map(employee, EmployeeDtoResponse.class);
    }
}
