package com.avwaveaf.customerservice.customer.service;

import ch.qos.logback.core.util.StringUtil;
import com.avwaveaf.customerservice.customer.model.Customer;
import com.avwaveaf.customerservice.customer.model.CustomerRequest;
import com.avwaveaf.customerservice.customer.model.CustomerResponse;
import com.avwaveaf.customerservice.customer.repository.CustomerRepository;
import com.avwaveaf.customerservice.exception.CustomerNotFoundException;
import com.avwaveaf.customerservice.mapper.service.CustomerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer(@Valid CustomerRequest customerRequest) {
        var customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
        return customer.getId();
    }

    public void updateCustomer(@Valid CustomerRequest customerRequest) {
        var customer = customerRepository.findById(customerRequest.id()).orElseThrow(() -> new CustomerNotFoundException(
                String.format("Cannot Update Customer:: No Customer Found with id:: %s", customerRequest.id())
        ));

        mergerCustomer(customer, customerRequest);
        customerRepository.save(customer);
    }

    private void mergerCustomer(Customer customer, CustomerRequest customerRequest) {
        if (StringUtils.isNotBlank(customerRequest.firstName())) {
            customer.setFirstName(customerRequest.firstName());
        }

        if (StringUtils.isNotBlank(customerRequest.lastName())) {
            customer.setLastName(customerRequest.lastName());
        }

        if (StringUtils.isNotBlank(customerRequest.email())) {
            customer.setEmail(customerRequest.email());
        }

        if (customerRequest.address() != null) {
            customer.setAddress(customerRequest.address());
        }
    }


    public List<CustomerResponse> findAllCustomer() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }


    public Boolean existsById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Cannot Find Customer:: No Customer Found with id:: %s", customerId)));
    }

    public void deleteById(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
