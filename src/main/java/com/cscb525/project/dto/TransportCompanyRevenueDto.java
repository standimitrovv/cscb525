package com.cscb525.project.dto;

import com.cscb525.project.model.revenue.Months;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransportCompanyRevenueDto {
    @NotBlank
    private Months forMonth;

    @NotBlank
    private double revenue;
}
