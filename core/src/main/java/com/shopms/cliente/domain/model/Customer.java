package com.shopms.cliente.domain.model;

import java.time.LocalDate;

public record Customer(String id, String name, LocalDate birthDate, Address address) {
}