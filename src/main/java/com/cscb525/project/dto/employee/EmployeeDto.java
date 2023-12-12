package com.cscb525.project.dto.employee;

import com.cscb525.project.model.employee.DrivingQualification;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    @NotBlank
    private String name;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private DrivingQualification drivingQualification;

    @NotBlank
    private double salary;
}