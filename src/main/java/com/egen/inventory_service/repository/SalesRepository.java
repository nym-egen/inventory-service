package com.egen.inventory_service.repository;

import com.egen.inventory_service.entities.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long>
        , QuerydslPredicateExecutor<Sales> {
}
