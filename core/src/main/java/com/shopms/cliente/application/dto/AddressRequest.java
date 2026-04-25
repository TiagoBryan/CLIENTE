package com.shopms.cliente.application.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressRequest(
        @NotBlank(message = "logradouro e obrigatorio") String street,
        @NotBlank(message = "numero e obrigatorio") String number,
        @NotBlank(message = "cidade e obrigatoria") String city,
        @NotBlank(message = "estado e obrigatorio") String state
) {
}