package com.shopms.cliente.application.dto;

import java.time.LocalDate;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        @NotBlank(message = "nome e obrigatorio") String name,
        @NotNull(message = "data de nascimento e obrigatoria") LocalDate birthDate,
        @Valid @NotNull(message = "endereco e obrigatorio") AddressRequest address
) {
}