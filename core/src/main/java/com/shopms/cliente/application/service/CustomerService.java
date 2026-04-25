package com.shopms.cliente.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shopms.cliente.application.dto.AddressRequest;
import com.shopms.cliente.application.dto.AddressResponse;
import com.shopms.cliente.application.dto.CustomerRequest;
import com.shopms.cliente.application.dto.CustomerResponse;
import com.shopms.cliente.application.exception.ResourceNotFoundException;
import com.shopms.cliente.domain.model.Address;
import com.shopms.cliente.domain.model.Customer;
import com.shopms.cliente.domain.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerResponse> findAll() {
        return customerRepository.findAll().stream().map(this::toResponse).toList();
    }

    public CustomerResponse findById(String id) {
        return toResponse(loadById(id));
    }

    public CustomerResponse create(CustomerRequest request) {
        Customer saved = customerRepository.save(new Customer(
                null,
                request.name().trim(),
                request.birthDate(),
                toAddress(request.address())
        ));
        return toResponse(saved);
    }

    public CustomerResponse update(String id, CustomerRequest request) {
        Customer existing = loadById(id);
        Customer updated = new Customer(
                existing.id(),
                request.name().trim(),
                request.birthDate(),
                toAddress(request.address())
        );
        return toResponse(customerRepository.save(updated));
    }

    public void delete(String id) {
        loadById(id);
        customerRepository.deleteById(id);
    }

    private Customer loadById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente nao encontrado"));
    }

    private Address toAddress(AddressRequest address) {
        return new Address(address.street().trim(), address.number().trim(), address.city().trim(), address.state().trim());
    }

    private CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.id(),
                customer.name(),
                customer.birthDate(),
                new AddressResponse(
                        customer.address().street(),
                        customer.address().number(),
                        customer.address().city(),
                        customer.address().state()
                )
        );
    }
}