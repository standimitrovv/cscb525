package com.cscb525.project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransportCompanyDtoResponse {

    @NotBlank
    private int id;

    @NotBlank
    private String name;
}
