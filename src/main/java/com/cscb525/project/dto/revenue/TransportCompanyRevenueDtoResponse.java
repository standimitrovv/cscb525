package com.cscb525.project.dto.revenue;

import com.cscb525.project.model.common.Months;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransportCompanyRevenueDtoResponse {
    private int id;

    private Months forMonth;

    private double revenue;
}
