package com.egen.inventory_service.service.mapper;

import com.egen.inventory_service.entities.Customer;
import com.egen.inventory_service.model.CustomerDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CustomerMapper implements Function<CustomerDto, Customer> {

    @Override
    public Customer apply(CustomerDto customerDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        return customer;
    }
}
