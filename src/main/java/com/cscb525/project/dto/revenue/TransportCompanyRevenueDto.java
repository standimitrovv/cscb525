package com.cscb525.project.dto.revenue;

import com.cscb525.project.model.revenue.Months;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransportCompanyRevenueDto {
    @NotNull(message = "The 'forMonth' field cannot be null!")
    @Enumerated(EnumType.STRING)
    private Months forMonth;

    @NotNull(message = "The 'revenue' field cannot be null!")
    @Positive(message = "The 'revenue' must be bigger than 0")
    @DecimalMin(value = "1000.0", message = "The 'revenue' field has to contain at least 4 digits")
    @DecimalMax(value = "9999999.0", message = "The 'revenue' field has to contain at most 7 digits")
    private double revenue;
}
