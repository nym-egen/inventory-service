package com.egen.inventory_service.service.mapper;

import com.egen.inventory_service.entities.Customer;
import com.egen.inventory_service.model.CustomerResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CustomerResponseMapper implements Function<Customer, CustomerResponse> {
    @Override
    public CustomerResponse apply(Customer customer) {
        CustomerResponse customerResponse = new CustomerResponse();
        BeanUtils.copyProperties(customer, customerResponse);
        return customerResponse;
    }
}
