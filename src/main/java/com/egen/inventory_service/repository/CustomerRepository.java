package com.egen.inventory_service.repository;

import com.egen.inventory_service.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>
        , QuerydslPredicateExecutor<Customer> {

    Optional<Customer> findByMobile(String mobile);

    Optional<Customer> findByEmail(String email);
}
