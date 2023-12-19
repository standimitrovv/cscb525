package com.cscb525.project.dto.employee;

import com.cscb525.project.model.employee.DrivingQualification;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    @NotBlank(message = "The 'name' field cannot be blank!")
    @Size(min = 2, max = 25, message = "The 'name' field has to contain at least 2 and at most 25 characters!")
    private String name;

    @NotNull(message = "The 'drivingQualification' field cannot be blank!")
    @Enumerated(EnumType.STRING)
    private DrivingQualification drivingQualification;

    @NotNull(message = "The 'salary' field cannot be blank!")
    @DecimalMin(value = "1000.0", message = "The 'salary' field has to contain at least 4 digits")
    @DecimalMax(value = "99999.0", message = "The 'salary' field has to contain at most 6 digits")
    private double salary;
}
