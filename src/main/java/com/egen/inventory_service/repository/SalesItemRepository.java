package com.egen.inventory_service.repository;

import com.egen.inventory_service.entities.SalesItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalesItemRepository extends JpaRepository<SalesItem, Long>
        , QuerydslPredicateExecutor<SalesItem> {

    List<SalesItem> findBySalesId(Long id);
    List<SalesItem> findAllBySalesId(Long id);
}
