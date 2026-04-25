package com.shopms.cliente.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.shopms.cliente.domain.model.Customer;
import com.shopms.cliente.domain.repository.CustomerRepository;
import com.shopms.cliente.infrastructure.persistence.mapper.CustomerPersistenceMapper;

@Repository
public class MongoCustomerRepositoryAdapter implements CustomerRepository {

    private final SpringDataCustomerRepository repository;
    private final CustomerPersistenceMapper mapper;

    public MongoCustomerRepositoryAdapter(SpringDataCustomerRepository repository, CustomerPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Customer> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Customer save(Customer customer) {
        return mapper.toDomain(repository.save(mapper.toDocument(customer)));
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}