package com.cscb525.project.repository;

import com.cscb525.project.model.employee.DrivingQualification;
import com.cscb525.project.model.employee.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE from Employee e where e.company.id = ?1")
    void deleteCompany(Integer companyId);

    // FILTERING

    // Filtering by driving qualification
    @Query(value = "SELECT e FROM Employee e WHERE e.drivingQualification = :drivingQualification")
    List<Employee> getAllEmployeesWithDrivingQualification(@Param("drivingQualification") DrivingQualification drivingQualification);

    // Filtering by salary
    @Query(value = "SELECT e FROM Employee e WHERE e.salary = :salary")
    List<Employee> getAllEmployeesWithSalaryEQTo(@Param("salary") double salary);

    @Query(value = "SELECT e FROM Employee e WHERE e.salary < :salary")
    List<Employee> getAllEmployeesWithSalaryLessThan(@Param("salary") double salary);

    @Query(value = "SELECT e FROM Employee e WHERE e.salary <= :salary")
    List<Employee> getAllEmployeesWithSalaryLessThanOrEQTo(@Param("salary") double salary);

    @Query(value = "SELECT e FROM Employee e WHERE e.salary > :salary")
    List<Employee> getAllEmployeesWithSalaryMoreThan(@Param("salary") double salary);

    @Query(value = "SELECT e FROM Employee e WHERE e.salary >= :salary")
    List<Employee> getAllEmployeesWithSalaryMoreThanOrEQTo(@Param("salary") double salary);


    // SORTING

    // Sorting by driving qualification
    @Query(value = "SELECT e FROM Employee e")
    List<Employee> getAllEmployeesOrderedByQualification(Sort sort);

    // Sorting by salary
    @Query(value = "SELECT e FROM Employee e")
    List<Employee> getAllEmployeesOrderedBySalary(Sort sort);
}
