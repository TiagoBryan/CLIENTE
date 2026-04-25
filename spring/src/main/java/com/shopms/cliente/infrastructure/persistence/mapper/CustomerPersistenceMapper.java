package com.shopms.cliente.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.shopms.cliente.domain.model.Address;
import com.shopms.cliente.domain.model.Customer;
import com.shopms.cliente.infrastructure.persistence.document.AddressDocument;
import com.shopms.cliente.infrastructure.persistence.document.CustomerDocument;

@Component
public class CustomerPersistenceMapper {

    public Customer toDomain(CustomerDocument document) {
        return new Customer(
                document.getId(),
                document.getName(),
                document.getBirthDate(),
                new Address(
                        document.getAddress().getStreet(),
                        document.getAddress().getNumber(),
                        document.getAddress().getCity(),
                        document.getAddress().getState()
                )
        );
    }

    public CustomerDocument toDocument(Customer customer) {
        AddressDocument addressDocument = new AddressDocument();
        addressDocument.setStreet(customer.address().street());
        addressDocument.setNumber(customer.address().number());
        addressDocument.setCity(customer.address().city());
        addressDocument.setState(customer.address().state());

        CustomerDocument document = new CustomerDocument();
        document.setId(customer.id());
        document.setName(customer.name());
        document.setBirthDate(customer.birthDate());
        document.setAddress(addressDocument);
        return document;
    }
}