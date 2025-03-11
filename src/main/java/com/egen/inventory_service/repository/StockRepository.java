package com.egen.inventory_service.repository;

import com.egen.inventory_service.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>
        , QuerydslPredicateExecutor<Stock> {

    Optional<Stock> findByItemId(Long id);

//    List<Stock> findItemById(Long id);
}
