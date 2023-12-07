package com.cscb525.project.dto;

import com.cscb525.project.model.Client;
import com.cscb525.project.model.CompanyRevenue;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransportCompanyDtoResponse {

    @NotBlank
    private int id;

    @NotBlank
    private String name;

    private Set<Client> clients;

    private List<CompanyRevenue> revenueList;
}
