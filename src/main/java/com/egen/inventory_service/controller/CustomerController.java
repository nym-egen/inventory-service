package com.egen.inventory_service.controller;

import com.egen.inventory_service.model.CustomerDto;
import com.egen.inventory_service.model.CustomerSearchDto;
import com.egen.inventory_service.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/customers")
//@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDto customerDto) {
        customerService.createCustomer(customerDto);
        return ResponseEntity.ok("Customer created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id,
                                            @RequestBody CustomerDto customerDto) {
        customerService.updateCustomer(id, customerDto);
        return ResponseEntity.ok("Customer Updated successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getCustomerByEmail(@PathVariable String email) {
        return ResponseEntity.ok(customerService.getCustomerByEmail(email));
    }

    @GetMapping("/mobile/{mobile}")
    public ResponseEntity<?> getCustomerByMobile(@PathVariable String mobile) {
        return ResponseEntity.ok(customerService.getCustomerByMobile(mobile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Successfully deleted");
    }

//    @GetMapping
//    public ResponseEntity<?> getAllCustomers() {
//        return ResponseEntity.ok(customerService.getAllCustomers());
//    }

    @PostMapping("/search-list")
    public ResponseEntity<?> searchList(@RequestBody CustomerSearchDto customerSearchDto) {
        return ResponseEntity.ok(customerService.searchList(customerSearchDto));
    }

    @PostMapping("/search-page")
    public ResponseEntity<?> searchPage(@RequestBody CustomerSearchDto customerSearchDto) {
        return ResponseEntity.ok(customerService.searchPage(customerSearchDto));
    }
}
