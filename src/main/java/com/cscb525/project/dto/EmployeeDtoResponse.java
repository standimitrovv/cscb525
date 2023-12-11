package com.cscb525.project.dto;

import com.cscb525.project.model.DrivingQualification;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDtoResponse {
    @NotBlank
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private DrivingQualification drivingQualification;

    @NotBlank
    private double salary;
}
