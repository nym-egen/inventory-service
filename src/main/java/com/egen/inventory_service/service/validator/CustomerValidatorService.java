package com.egen.inventory_service.service.validator;

import com.egen.inventory_service.entities.Customer;
import com.egen.inventory_service.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class CustomerValidatorService {

    private final CustomerRepository customerRepository;

    public CustomerValidatorService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer ifFoundByIdReturnElseThrow(Long id) {
        return customerRepository.findById(id).orElseThrow(() ->
                new RuntimeException(String
                        .format("Customer with id [%s] not found", id)));
    }

    public Customer ifFoundByEmailReturnElseThrow(String email) {
        return customerRepository.findByEmail(email).orElseThrow(() ->
                new RuntimeException(String
                        .format("Customer with id [%s] not found", email)));
    }

    public Customer ifFoundByMobileReturnElseThrow(String mobile) {
        return customerRepository.findByMobile(mobile).orElseThrow(() ->
                new RuntimeException(String
                        .format("Customer with id [%s] not found", mobile)));
    }
}
