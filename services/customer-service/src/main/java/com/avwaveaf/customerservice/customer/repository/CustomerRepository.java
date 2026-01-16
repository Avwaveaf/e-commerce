package com.avwaveaf.customerservice.customer.repository;


import com.avwaveaf.customerservice.customer.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
