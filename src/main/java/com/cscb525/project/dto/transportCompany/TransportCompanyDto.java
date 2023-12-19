package com.cscb525.project.dto.transportCompany;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransportCompanyDto {
    @NotBlank(message = "The 'name' field cannot be blank!")
    @Size(min = 2, max = 50, message = "The 'name' field has to contain at least 2 and at most 50 characters!")
    private String name;
}
