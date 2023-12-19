package com.cscb525.project.dto.client;

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
public class ClientDto {
    @NotBlank(message = "Client name cannot be blank!")
    @Size(min = 2, max = 25, message = "The 'name' field has to contain at least 2 and at most 25 characters!")
    private String name;
}
