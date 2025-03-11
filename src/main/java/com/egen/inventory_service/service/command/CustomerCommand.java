package com.egen.inventory_service.service.command;

import com.egen.inventory_service.entities.Customer;
import com.egen.inventory_service.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class CustomerCommand {
    private final CustomerRepository customerRepository;

    public CustomerCommand(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer saveAndReturn(Customer customer) {
        return customerRepository.save(customer);
    }

    public void update(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer updateAndReturn(Customer customer) {
        return customerRepository.save(customer);
    }

    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }
}
