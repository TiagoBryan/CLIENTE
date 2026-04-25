package com.shopms.cliente.application.dto;

import java.time.LocalDate;

public record CustomerResponse(String id, String name, LocalDate birthDate, AddressResponse address) {
}