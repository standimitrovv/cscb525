package com.cscb525.project.dto.revenue;

import com.cscb525.project.model.revenue.Months;
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
    private Integer id;

    @NotBlank
    private Months forMonth;

    @NotBlank
    private double revenue;
}
