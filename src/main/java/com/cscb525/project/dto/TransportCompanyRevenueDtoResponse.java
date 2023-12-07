package com.cscb525.project.dto;

import com.cscb525.project.model.Months;
import com.cscb525.project.model.TransportCompany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransportCompanyRevenueDtoResponse {
    @NotBlank
    private Months month;

    @NotBlank
    private double revenue;

    @NotBlank
    private TransportCompany company;
}
