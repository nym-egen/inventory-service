package com.egen.inventory_service.service;

import com.egen.inventory_service.entities.Customer;
import com.egen.inventory_service.entities.Sales;
import com.egen.inventory_service.model.CustomerDto;
import com.egen.inventory_service.model.CustomerResponse;
import com.egen.inventory_service.model.CustomerSearchDto;
import com.egen.inventory_service.repository.CustomerRepository;
import com.egen.inventory_service.service.mapper.CustomerMapper;
import com.egen.inventory_service.service.mapper.CustomerResponseMapper;
import com.egen.inventory_service.service.query.CustomerQueryService;
import com.egen.inventory_service.service.validator.CustomerValidatorService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerValidatorService customerValidatorService;
    private final CustomerQueryService customerQueryService;
    private final CustomerMapper customerMapper;
    private final CustomerResponseMapper customerResponseMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerValidatorService customerValidatorService, CustomerQueryService customerQueryService, CustomerMapper customerMapper, CustomerResponseMapper customerResponseMapper) {
        this.customerRepository = customerRepository;
        this.customerValidatorService = customerValidatorService;
        this.customerQueryService = customerQueryService;
        this.customerMapper = customerMapper;
        this.customerResponseMapper = customerResponseMapper;
    }

    public void createCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.apply(customerDto);
        customerRepository.save(customer);
    }

    public void updateCustomer(Long id, CustomerDto customerDto) {
        Customer customer = customerValidatorService.ifFoundByIdReturnElseThrow(id);
        BeanUtils.copyProperties(customerDto, customer);
        customerRepository.save(customer);
    }

    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerValidatorService.ifFoundByIdReturnElseThrow(id);
        return customerResponseMapper.apply(customer);
    }

    public CustomerResponse getCustomerByEmail(String email) {
        Customer customer = customerValidatorService.ifFoundByEmailReturnElseThrow(email);
        return customerResponseMapper.apply(customer);
    }

    public CustomerResponse getCustomerByMobile(String mobile) {
        Customer customer = customerValidatorService.ifFoundByMobileReturnElseThrow(mobile);
        return customerResponseMapper.apply(customer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerValidatorService.ifFoundByIdReturnElseThrow(id);
        customerRepository.delete(customer);
    }

    public List<CustomerResponse> searchList(CustomerSearchDto customerSearchDto) {
        return customerQueryService.searchList(customerSearchDto).stream()
                .map(customer -> {
                    CustomerResponse customerResponse = customerResponseMapper.apply(customer);
                    customerResponse.setDueAmount(customer.getSalesList().stream()
                            .mapToDouble(Sales::getDueAmount).sum());
                    return customerResponse;
                }).toList();
    }

    public Page<CustomerResponse> searchPage(CustomerSearchDto customerSearchDto) {
        Page<Customer> page = customerQueryService.searchPage(customerSearchDto);
        List<CustomerResponse> resultList = page.getContent().stream()
                .map(customer -> {
                    CustomerResponse customerResponse = customerResponseMapper.apply(customer);
                    customerResponse.setDueAmount(customer.getSalesList().stream()
                            .mapToDouble(Sales::getDueAmount).sum());
                    return customerResponse;
                }).toList();
        return new PageImpl<>(resultList, page.getPageable(), page.getTotalElements());
    }

//    public List<CustomerResponse> searchPage(CustomerSearchDto customerSearchDto) {
//        return Utils.convertToList(customerRepository
//                        .findAll(CustomerPredicate.search(customerSearchDto), Sort.by("name")))
//                .stream().map(customer -> {
//                    CustomerResponse customerResponse = new CustomerResponse();
//                    BeanUtils.copyProperties(customer, customerResponse);
//                    return customerResponse;
//                }).toList();
//    }
}
