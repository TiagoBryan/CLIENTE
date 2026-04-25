package com.shopms.cliente.infrastructure.persistence.document;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
public class CustomerDocument {

    @Id
    private String id;

    private String name;

    private LocalDate birthDate;

    private AddressDocument address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public AddressDocument getAddress() {
        return address;
    }

    public void setAddress(AddressDocument address) {
        this.address = address;
    }
}