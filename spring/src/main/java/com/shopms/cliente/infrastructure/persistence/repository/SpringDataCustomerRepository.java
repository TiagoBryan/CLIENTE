package com.shopms.cliente.infrastructure.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shopms.cliente.infrastructure.persistence.document.CustomerDocument;

public interface SpringDataCustomerRepository extends MongoRepository<CustomerDocument, String> {
}